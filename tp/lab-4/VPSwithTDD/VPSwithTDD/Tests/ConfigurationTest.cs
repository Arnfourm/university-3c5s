using NUnit.Framework;
using VPSwithTDD.Models;

namespace VPSwithTDD.tests
{
    [TestFixture]
    public class ConfigurationTest
    {
        [Test]
        public void CreateConfiguration()
        {
            Configuration config = new Configuration("Windows 11", "Amd ryzon X3D", 5.8f, "RTX 4090", 24, 128, 2000);

            Assert.That(config.GetOs(), Is.EqualTo("Windows 11"));
            Assert.That(config.GetCpuName(), Is.EqualTo("Amd ryzon X3D"));
            Assert.That(config.GetCpuGhz(), Is.EqualTo(5.8f));
            Assert.That(config.GetRam(), Is.EqualTo(128));
            Assert.That(config.GetDiskSize(), Is.EqualTo(2000));
        }

        [Test]
        public void CreateConfigurationNullOsException()
        {
            Assert.Throws<ArgumentNullException>(() => 
                new Configuration(null, "CPU", 3.0f, "GPU", 16, 32, 500));
        }

        [Test]
        public void CreateConfigurationInvalidCpuGhzValueException()
        {
            Assert.Throws<ArgumentException>(() =>
                new Configuration("OS", "CPU", 0, "GPU", 8, 32, 500));
            Assert.Throws<ArgumentException>(() =>
                new Configuration("OS", "CPU", -1.2f, "GPU", 8, 32, 500));
        }

        [Test]
        public void CreateConfigurationInvalidRamValueException()
        {
            Assert.Throws<ArgumentException>(() => 
                new Configuration("OS", "CPU", 2.5f, "GPU", 8, 0, 250));
            Assert.Throws<ArgumentException>(() => 
                new Configuration("OS", "CPU", 2.5f, "GPU", 8, -2, 250));
        }
    }
}
