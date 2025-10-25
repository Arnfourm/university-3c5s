using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using rentVPSmicroservices.microserviceConfigs.Models;
using rentVPSmicroservices.microserviceConfig.Contexts;

namespace rentVPSmicroservices.microserviceConfigs.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ConfigurationsController : ControllerBase
    {
        private readonly ConfigContext _context;

        public ConfigurationsController(ConfigContext context)
        {
            _context = context;
        }

        // GET: api/Configurations
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Configuration>>> GetConfigurations()
        {
            return await _context.Configurations.ToListAsync();
        }

        // GET: api/Configurations/5
        [HttpGet("{id:Guid}")]
        public async Task<ActionResult<Configuration>> GetConfiguration(Guid id)
        {
            var configuration = await _context.Configurations.FindAsync(id);

            if (configuration == null)
            {
                return NotFound();
            }

            return configuration;
        }

        // PUT: api/Configurations/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id:Guid}")]
        public async Task<IActionResult> PutConfiguration(Guid id, Configuration configuration)
        {
            if (id != configuration.Id)
            {
                return BadRequest();
            }

            _context.Entry(configuration).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ConfigurationExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Configurations
        [HttpPost]
        public async Task<ActionResult<Configuration>> PostConfiguration(Configuration configuration)
        {
            _context.Configurations.Add(configuration);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetConfiguration", new { id = configuration.Id }, configuration);
        }

        // DELETE: api/Configurations/5
        [HttpDelete("{id:Guid}")]
        public async Task<IActionResult> DeleteConfiguration(Guid id)
        {
            var configuration = await _context.Configurations.FindAsync(id);
            if (configuration == null)
            {
                return NotFound();
            }

            _context.Configurations.Remove(configuration);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool ConfigurationExists(Guid id)
        {
            return _context.Configurations.Any(e => e.Id == id);
        }
    }
}
