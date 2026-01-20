using Microsoft.AspNetCore.Mvc;
using NUnit.Framework;
using VPSwithTDD.Controllers;
using VPSwithTDD.Models;

namespace VPSwithTDD.Tests
{
    [TestFixture]
    public class ConfigurationControllerTest
    {
        private ConfigurationController _controller;

        [SetUp]
        public void Setup()
        {
            _controller = new ConfigurationController();
        }

        [Test]
        public void GetConfigurationsReturnOkResult()
        {
            var result = _controller.GetConfigurations();

            Assert.That(result.Result, Is.Not.Null);
            Assert.That(result.Result, Is.TypeOf<OkObjectResult>());
        }

        [Test]
        public void GetConfigurationsTestNotEmpty()
        {
            OkObjectResult okResult = (OkObjectResult)_controller.GetConfigurations().Result;
            List<Configuration> result = (List < Configuration > )okResult.Value;

            Assert.That(result, Is.Not.Empty);
        }

        [Test]
        public void GetConfigurationsTest()
        {
            var expectedConfigs = new List<Configuration>
            {
                new Configuration("Windows 11", "Intel i7-12700K", 5.0f, "RTX 4090", 24, 64, 1024),
                new Configuration("Ubuntu 22.04", "AMD Ryzen 9", 4.8f, "RTX 5090", 40, 128, 2048),
                new Configuration("MacOS", "M5", 3.2f, "-", 0, 256, 4096)
            };

            OkObjectResult okResult = (OkObjectResult)_controller.GetConfigurations().Result;
            List<Configuration> result = (List<Configuration>)okResult.Value;

            Assert.That(result.Count, Is.EqualTo(expectedConfigs.Count));
            Assert.That(result[0].GetOs(), Is.EqualTo(expectedConfigs[0].GetOs()));
            Assert.That(result[1].GetCpuGhz(), Is.EqualTo(expectedConfigs[1].GetCpuGhz()));
        }

        [Test]
        public void GetConfigurationByIdOkResult()
        {
            Guid configId = Guid.Parse("5fc0ef0e-865f-4610-bf68-f842eb15779e");

            var result = _controller.GetConfigurationById(configId);

            Assert.That(result.Result, Is.Not.Null);
            Assert.That(result.Result, Is.TypeOf<OkObjectResult>());
        }

        [Test]
        public void GetConfigurationByIdTest()
        {
            Configuration expectConfig = new Configuration(
                Guid.Parse("5fc0ef0e-865f-4610-bf68-f842eb15779e"), 
                "Windows 11", 
                "Intel i7-12700K", 
                5.0f, 
                "RTX 4090", 
                24, 64, 1024
            );

            OkObjectResult okResult = (OkObjectResult)_controller.GetConfigurationById(expectConfig.GetId()).Result;
            Configuration result = (Configuration)okResult.Value;

            Assert.That(result.GetId(), Is.EqualTo(expectConfig.GetId()));
            Assert.That(result.GetDiskSize(), Is.EqualTo(expectConfig.GetDiskSize()));
        }

        [Test]
        public void GetConfigurationIdInvalid()
        {
            Guid wrongId = Guid.Parse("5fc0ef0e-865f-4610-bf68-f842eb157792");

            var result = _controller.GetConfigurationById(wrongId);

            Assert.That(result.Result, Is.InstanceOf<NotFoundResult>());
        }

        [Test]
        public void CreateConfigurationTestOkResult()
        {
            Configuration newConfig = new Configuration(
                Guid.NewGuid(),
                "Windows 7",
                "Xeon",
                3.5f,
                "GTX 1060",
                10, 32, 500
            );

            var result = _controller.CreateConfiguration(newConfig);

            Assert.That(result.Result, Is.Not.Null);
            Assert.That(result.Result, Is.TypeOf<OkObjectResult>());
        }

        [Test]
        public void CreateConfigurationTest()
        {
            Configuration newConfig = new Configuration(
                Guid.NewGuid(),
                "Windows 7",
                "Xeon",
                3.5f,
                "GTX 1060",
                10, 32, 500
            );

            OkObjectResult okResult = (OkObjectResult)_controller.CreateConfiguration(newConfig).Result;
            Configuration result = (Configuration)okResult.Value;

            Assert.That(result, Is.Not.Null);
            Assert.That(result.GetId(), Is.EqualTo(newConfig.GetId()));
            Assert.That(result.GetGpuName(), Is.EqualTo(newConfig.GetGpuName()));
        }

        [Test]
        public void DeleteConfigurationTestOkResult()
        {
            Guid configId = Guid.Parse("5fc0ef0e-865f-4610-bf68-f842eb15779e");

            var result = _controller.DeleteConfiguration(configId);

            Assert.That(result, Is.Not.Null);
            Assert.That(result, Is.TypeOf<NoContentResult>());
        }

        [Test]
        public void DeleteConfigurationTest()
        {
            var expectedConfigs = new List<Configuration>
            {
                new Configuration(Guid.Parse("5fc0ef0e-865f-4610-bf68-f842eb15779e"), "Windows 11", "Intel i7-12700K", 5.0f, "RTX 4090", 24, 64, 1024),
                new Configuration(Guid.Parse("4a01cbab-51b4-4196-8d65-26f2f5536eac"), "Ubuntu 22.04", "AMD Ryzen 9", 4.8f, "RTX 5090", 40, 128, 2048),
                new Configuration(Guid.Parse("c31fecf5-d35e-4d40-8cc5-76c95d4177dc"), "MacOS", "M5", 3.2f, "-", 0, 256, 4096)
            };

            _controller.DeleteConfiguration(expectedConfigs[0].GetId());

            OkObjectResult okResult = (OkObjectResult)_controller.GetConfigurations().Result;
            List<Configuration> configs = (List<Configuration>)okResult.Value;

            Assert.That(configs.Count, Is.EqualTo(expectedConfigs.Count - 1));
            Assert.That(configs.Any(c => c.GetId() == expectedConfigs[0].GetId()), Is.False);
        }

        [Test]
        public void DeleteConfigurationTestInvalid()
        {
            Guid wrongId = Guid.Parse("5fc0ef0e-865f-4610-bf68-f842eb157792");

            var result = _controller.DeleteConfiguration(wrongId);

            Assert.That(result, Is.InstanceOf<NotFoundResult>());
        }
    }
};