using Microsoft.EntityFrameworkCore;
using rentVPSmicroservices.microserviceOrders.Models;

namespace rentVPSmicroservices.microserviceOrder.Contexts
{
    public class OrderContext : DbContext
    {
        public OrderContext(DbContextOptions<OrderContext> options) : base(options)
        {
        }

        public DbSet<Order> Orders { get; set; }
    }
}
