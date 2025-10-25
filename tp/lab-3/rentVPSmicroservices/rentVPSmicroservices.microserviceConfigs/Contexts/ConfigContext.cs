using Microsoft.EntityFrameworkCore;
using rentVPSmicroservices.microserviceConfigs.Models;

namespace rentVPSmicroservices.microserviceUser.Contexts
{
    public class ConfigContext : DbContext
    {
        public ConfigContext(DbContextOptions<ConfigContext> options) : base(options)
        {
        }

        public DbSet<Configuration> Configurations { get; set; }
    }
}
