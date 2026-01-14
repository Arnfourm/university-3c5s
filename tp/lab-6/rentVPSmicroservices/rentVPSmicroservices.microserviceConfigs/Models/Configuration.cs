using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace rentVPSmicroservices.microserviceConfigs.Models
{
    public class Configuration
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Key]
        public Guid Id { get; set; }
        public required string OS { get; set; }
        public required string CPU_name { get; set; }
        public float CPU_ghz { get; set; }
        public required string GPU_name { get; set; } 
        public int GPU_vram { get; set; }
        public int RAM { get; set; }
        public int disk_size { get; set; }

        public Configuration(string oS, string cPU_name, float cPU_ghz, string gPU_name, int gPU_vram, int rAM, int disk_size)
        {
            OS = oS;
            CPU_name = cPU_name;
            CPU_ghz = cPU_ghz;
            GPU_name = gPU_name;
            GPU_vram = gPU_vram;
            RAM = rAM;
            this.disk_size = disk_size;
        }
    }
}
