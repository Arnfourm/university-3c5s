using Microsoft.EntityFrameworkCore;
using rentVPSmicroservices.microserviceUser.Models;

namespace rentVPSmicroservices.microserviceUser.Contexts
{
    public class UserContext : DbContext
    {
        public UserContext(DbContextOptions<UserContext> options) : base(options)
        {
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Admin> Admins { get; set; }
    }
}
