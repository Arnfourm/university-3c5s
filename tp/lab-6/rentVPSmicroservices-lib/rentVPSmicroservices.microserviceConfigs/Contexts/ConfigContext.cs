using Microsoft.EntityFrameworkCore;
using rentVPSmicroservices.microserviceConfigs.Models;

namespace rentVPSmicroservices.microserviceConfig.Contexts
{
    public class ConfigContext : DbContext
    {
        public ConfigContext(DbContextOptions<ConfigContext> options) : base(options)
        {
        }

        public DbSet<Configuration> Configurations { get; set; }
    }
}
