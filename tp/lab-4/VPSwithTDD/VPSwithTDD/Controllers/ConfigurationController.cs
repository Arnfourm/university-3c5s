using Microsoft.AspNetCore.Mvc;
using VPSwithTDD.Models;

namespace VPSwithTDD.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class ConfigurationController : ControllerBase
    {
        private List<Configuration> configurations;

        public ConfigurationController()
        {
            configurations = new List<Configuration>
            {
                new Configuration(Guid.Parse("5fc0ef0e-865f-4610-bf68-f842eb15779e"), "Windows 11", "Intel i7-12700K", 5.0f, "RTX 4090", 24, 64, 1024),
                new Configuration(Guid.Parse("4a01cbab-51b4-4196-8d65-26f2f5536eac"), "Ubuntu 22.04", "AMD Ryzen 9", 4.8f, "RTX 5090", 40, 128, 2048),
                new Configuration(Guid.Parse("c31fecf5-d35e-4d40-8cc5-76c95d4177dc"), "MacOS", "M5", 3.2f, "-", 0, 256, 4096)
            };
        }

        [HttpGet]
        public ActionResult<List<Configuration>> GetConfigurations()
        {
            return Ok(configurations);
        }

        [HttpGet("{id:Guid}")]
        public ActionResult<Configuration> GetConfigurationById(Guid id)
        {
            Configuration config = configurations
                .Where(config => config.GetId() == id)
                .FirstOrDefault();

           if (config == null)
            {
                return NotFound();
            }

           return Ok(config);
        }

        [HttpPost]
        public ActionResult<Configuration> CreateConfiguration([FromBody] Configuration configuration)
        {
            configurations.Add(configuration);

            Configuration configurationRes = configurations
                .Where(conf => conf.GetId() == configuration.GetId())
                .FirstOrDefault();

            return Ok(configurationRes);
        }

        [HttpDelete("{id:Guid}")]
        public ActionResult DeleteConfiguration(Guid id)
        {
            if (!configurations.Any(c => c.GetId() == id))
            {
                return NotFound();
            }

            configurations.RemoveAll(c => c.GetId() == id);

            return NoContent();
        }
    }
}
