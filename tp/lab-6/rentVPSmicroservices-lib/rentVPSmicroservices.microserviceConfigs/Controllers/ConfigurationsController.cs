using log4net;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using rentVPSmicroservices.microserviceConfig.Contexts;
using rentVPSmicroservices.microserviceConfigs.Models;
using rentVPSserviceLib;

namespace rentVPSmicroservices.microserviceConfigs.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ConfigurationsController : ControllerBase
    {
        private readonly ConfigContext _context;

        private readonly ILog _log = LogManager.GetLogger(typeof(ConfigurationsController));

        public ConfigurationsController(ConfigContext context)
        {
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Configuration>>> GetConfigurations()
        {
            List<Configuration> configurations = await _context.Configurations.ToListAsync();

            foreach (Configuration config in configurations)
            {
                _log.Info(Logging.FormattingLogWatchMessage(nameof(GetConfigurations), config.Id));
            }

            return await _context.Configurations.ToListAsync();
        }

        [HttpGet("{id:Guid}")]
        public async Task<ActionResult<Configuration>> GetConfigurationById(Guid id)
        {
            var configuration = await _context.Configurations.FindAsync(id);

            if (configuration == null)
            {
                _log.Error(Logging.FormattingLogNoExist(nameof(GetConfigurationById), id));

                return NotFound();
            }

            _log.Info(Logging.FormattingLogWatchMessage(nameof(GetConfigurationById), configuration.Id));

            return configuration;
        }

        [HttpPut("{id:Guid}")]
        public async Task<IActionResult> PutConfiguration(Guid id, Configuration configuration)
        {
            var foundConfig = await _context.Configurations.FindAsync(id);

            if (foundConfig == null)
            {
                _log.Error(Logging.FormattingLogNoExist(nameof(GetConfigurationById), id));

                return BadRequest();
            }

            configuration.Id = id;
            _context.Entry(configuration).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();

                _log.Info(Logging.FormattingLogUpdateMessage(nameof(PutConfiguration), configuration.Id));
            }
            catch (DbUpdateConcurrencyException)
            {
                _log.Error(Logging.FormattingLogSaveError(nameof(PutConfiguration)));

                throw;
            }

            return NoContent();
        }

        [HttpPost]
        public async Task<ActionResult<Configuration>> PostConfiguration(Configuration configuration)
        {
            configuration.Id = Guid.NewGuid();

            _context.Configurations.Add(configuration);
            await _context.SaveChangesAsync();

            _log.Info(Logging.FormattingLogCreateMessage(nameof(PostConfiguration), configuration.Id));

            return CreatedAtAction(nameof(GetConfigurationById), new { id = configuration.Id }, configuration);
        }

        [HttpDelete("{id:Guid}")]
        public async Task<IActionResult> DeleteConfiguration(Guid id)
        {
            var configuration = await _context.Configurations.FindAsync(id);
            if (configuration == null)
            {
                _log.Error(Logging.FormattingLogNoExist(nameof(GetConfigurationById), id));

                return NotFound();
            }

            _context.Configurations.Remove(configuration);
            await _context.SaveChangesAsync();

            _log.Error(Logging.FormattingLogCreateMessage(nameof(DeleteConfiguration), id));

            return NoContent();
        }
    }
}
