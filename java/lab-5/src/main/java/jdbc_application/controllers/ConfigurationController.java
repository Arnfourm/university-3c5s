package jdbc_application.controllers;

import jdbc_application.DAO.ConfigurationDAO;
import jdbc_application.models.Configurations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ConfigurationController {

    private final ConfigurationDAO _configurationDAO;

    public ConfigurationController(ConfigurationDAO configurationDAO)
    {
        _configurationDAO = configurationDAO;
    }

    @GetMapping("/configs")
    public String GetConfigurations(Model model)
    {
        List<Configurations> configurationsList = _configurationDAO.GetConfigurations();
        
        model.addAttribute("configList", configurationsList);

        return "config";
    }

    @PostMapping("/configs")
    public String CreateConfiguration(@ModelAttribute Configurations config)
    {
        _configurationDAO.CreateConfiguration(
            config.getCpuName(),
            config.getCpuGhz(),
            config.getRamVolume(),
            config.getDiskVolume()
        );

        return "redirect:/configs";
    }

    @PostMapping("/configs/delete")
    public String DeleteConfiguration(@RequestParam("configid") int configid)
    {
        _configurationDAO.DeleteConfiguration(configid);

        return "redirect:/configs";
    }
}
