using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using rentVPSmicroservices.microserviceOrder.Contexts;
using rentVPSmicroservices.microserviceOrders.Models;

namespace rentVPSmicroservices.microserviceOrders.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OrdersController : ControllerBase
    {
        private readonly OrderContext _context;
        private readonly string _userServiceAddress = "http://localhost:5198/api/Users";
        private readonly string _cconfigServiceAddress = "http://localhost:5082/api/Configurations";

        public OrdersController(OrderContext context)
        {
            _context = context;
        }

        // GET: api/Orders
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Order>>> GetOrders()
        {
            return await _context.Orders.ToListAsync();
        }

        // GET: api/Orders/5
        [HttpGet("{id:Guid}")]
        public async Task<ActionResult<Order>> GetOrder(Guid id)
        {
            var order = await _context.Orders.FindAsync(id);

            if (order == null)
            {
                return NotFound();
            }

            return order;
        }

        // PUT: api/Orders/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id:Guid}")]
        public async Task<IActionResult> PutOrder(Guid id, Order order)
        {
            if (id != order.id)
            {
                return BadRequest();
            }

            HttpClientHandler handler = new HttpClientHandler();

            using (HttpClient client = new HttpClient())
            {
                HttpResponseMessage responseUser = await client.GetAsync($"{_userServiceAddress}/{order.userGuid}");
                HttpResponseMessage responseConfig = await client.GetAsync($"{_cconfigServiceAddress}/{order.configGuid}");

                if (!responseUser.IsSuccessStatusCode)
                {

                    return NotFound("User Guid is uncorrect");
                }

                if (!responseConfig.IsSuccessStatusCode)
                {

                    return NotFound("Config Guid is uncorrect");
                }
            }

            _context.Entry(order).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!OrderExists(id))
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

        // POST: api/Orders
        [HttpPost]
        public async Task<ActionResult<Order>> PostOrder(Order order)
        {
            HttpClientHandler handler = new HttpClientHandler();

            using (HttpClient client = new HttpClient())
            {
                HttpResponseMessage responseUser = await client.GetAsync($"{_userServiceAddress}/{order.userGuid}");
                HttpResponseMessage responseConfig = await client.GetAsync($"{_cconfigServiceAddress}/{order.configGuid}");

                if (!responseUser.IsSuccessStatusCode)
                {

                    return NotFound("User Guid is uncorrect");
                }

                if (!responseConfig.IsSuccessStatusCode)
                {

                    return NotFound("Config Guid is uncorrect");
                }
            }

            _context.Orders.Add(order);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetOrder", new { id = order.id }, order);
        }

        // DELETE: api/Orders/5
        [HttpDelete("{id:Guid}")]
        public async Task<IActionResult> DeleteOrder(Guid id)
        {
            var order = await _context.Orders.FindAsync(id);
            if (order == null)
            {
                return NotFound();
            }

            _context.Orders.Remove(order);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool OrderExists(Guid id)
        {
            return _context.Orders.Any(e => e.id == id);
        }
    }
}
