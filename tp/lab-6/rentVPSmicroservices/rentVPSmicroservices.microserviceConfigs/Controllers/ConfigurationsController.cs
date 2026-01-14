using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using rentVPSmicroservices.microserviceConfigs.Models;
using rentVPSmicroservices.microserviceConfig.Contexts;
using log4net;

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
            _log.Info("Был вызван метод GetConfiguration()");
            _log.Info("All configuration catalog was watched");

            return await _context.Configurations.ToListAsync();
        }

        [HttpGet("{id:Guid}")]
        public async Task<ActionResult<Configuration>> GetConfigurationById(Guid id)
        {
            _log.Info("Был вызван метод GetConfigurationById()");

            var configuration = await _context.Configurations.FindAsync(id);

            if (configuration == null)
            {
                _log.Error($"Configuration with id {id} doesn't exist");

                return NotFound();
            }

            _log.Info($"Configuration with id {id} was watched");

            return configuration;
        }

        [HttpPut("{id:Guid}")]
        public async Task<IActionResult> PutConfiguration(Guid id, Configuration configuration)
        {
            _log.Info("Был вызван метод PutConfiguration()");

            var foundConfig = await _context.Configurations.FindAsync(id);

            if (foundConfig == null)
            {
                _log.Error($"Configuration with id {id} doesn't exist");

                return BadRequest();
            }

            configuration.Id = id;
            _context.Entry(configuration).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();

                _log.Info($"Конфигурация под id {configuration.Id} была изменена");
            }
            catch (DbUpdateConcurrencyException)
            {
                _log.Error($"Exception while trying to save new config");

                throw;
            }

            return NoContent();
        }

        [HttpPost]
        public async Task<ActionResult<Configuration>> PostConfiguration(Configuration configuration)
        {
            _log.Info("Был вызван метод PostConfiguration()");

            configuration.Id = Guid.NewGuid();

            _context.Configurations.Add(configuration);
            await _context.SaveChangesAsync();

            _log.Info($"Была добавлена новая конфигурация под id {configuration.Id}");

            return CreatedAtAction(nameof(GetConfigurationById), new { id = configuration.Id }, configuration);
        }

        [HttpDelete("{id:Guid}")]
        public async Task<IActionResult> DeleteConfiguration(Guid id)
        {
            _log.Info("Был вызван метод DeleteConfiguration()");

            var configuration = await _context.Configurations.FindAsync(id);
            if (configuration == null)
            {
                _log.Error($"Configuration with id {id} doesn't exist");

                return NotFound();
            }

            _context.Configurations.Remove(configuration);
            await _context.SaveChangesAsync();

            _log.Info($"Configuration with id {id} was deleted");

            return NoContent();
        }
    }
}
