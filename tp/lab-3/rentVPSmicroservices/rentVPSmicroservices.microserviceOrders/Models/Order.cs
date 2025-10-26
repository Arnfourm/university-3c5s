using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace rentVPSmicroservices.microserviceOrders.Models
{
    public class Order
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Key]
        public Guid id { get; set; }
        public Guid userGuid { get; set; }
        public Guid configGuid { get; set; }
        public DateOnly date { get; set; }
        public float rentPerMonth { get; set; }
        
        public Order (Guid userGuid, Guid configGuid, float rentPerMonth)
        {
            this.userGuid = userGuid;
            this.configGuid = configGuid;
            this.date = DateOnly.FromDateTime(DateTime.Now);
            this.rentPerMonth = rentPerMonth;
        }
    }
}
