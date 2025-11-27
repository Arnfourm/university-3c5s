package jdbc_application.controllers;

import jdbc_application.DAO.ConfigurationDAO;
import jdbc_application.models.Configurations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        return "config";
    }
}
