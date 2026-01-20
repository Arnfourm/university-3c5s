namespace VPSwithTDD.Models
{
    public class Configuration
    {
        private Guid Id;
        private string OS;
        private string CPU_name;
        private float CPU_ghz;
        private string GPU_name;
        private int GPU_vram;
        private int RAM;
        private int Disk_size;
        public Guid GetId() { return Id; }
        public string GetOs() { return OS; }
        public string GetCpuName() { return CPU_name; }
        public float GetCpuGhz() { return CPU_ghz; }
        public string GetGpuName() { return GPU_name; }
        public int GetGpuVram() { return GPU_vram; }
        public int GetRam() { return RAM; }
        public int GetDiskSize() { return Disk_size; }

        public Configuration(string os, string cpuName, float cpuGhz,
                            string gpuName, int gpuVram, int ram, int diskSize)
        {
            if (os == null)
                throw new ArgumentNullException("Os can't be null", nameof(os));
            if (cpuName == null)
                throw new ArgumentNullException("Cpu name can't be null", nameof(cpuName));
            if (gpuName == null)
                throw new ArgumentNullException("Gpu name can't be null", nameof(gpuName));

            if (cpuGhz <= 0)
                throw new ArgumentException("CPU ghz should be more than zero", nameof(cpuGhz));

            if (gpuVram < 0)
                throw new ArgumentException("VRAM can't be less than zero", nameof(gpuVram));

            if (ram <= 0)
                throw new ArgumentException("RAM should be more than zero", nameof(ram));

            if (diskSize <= 0)
                throw new ArgumentException("Disk size should be more than zero", nameof(diskSize));

            OS = os;
            CPU_name = cpuName;
            CPU_ghz = cpuGhz;
            GPU_name = gpuName;
            GPU_vram = gpuVram;
            RAM = ram;
            Disk_size = diskSize;
        }
        public Configuration(Guid id, string os, string cpuName, float cpuGhz,
                            string gpuName, int gpuVram, int ram, int diskSize)
                            : this(os, cpuName, cpuGhz, gpuName, gpuVram, ram, diskSize)
        {
            Id = id;
        }
    }
}
