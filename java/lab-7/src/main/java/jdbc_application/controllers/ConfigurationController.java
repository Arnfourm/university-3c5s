package jdbc_application.controllers;

import jdbc_application.DAO.ConfigurationDAO;
import jdbc_application.models.Configurations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

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
        
        model.addAttribute("config", new Configurations());

        return "config";
    }

    @PostMapping("/configs")
    public String CreateConfiguration(@ModelAttribute @Valid Configurations config)
    {
        _configurationDAO.CreateConfiguration(config);

        return "redirect:/configs";
    }
    @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex, Model model)
    {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        model.addAttribute("exception", errorMessage);

        return ResponseEntity.badRequest().body("Validation Error: " + errorMessage);
    }

    @PostMapping("/configs/delete")
    public String DeleteConfiguration(@RequestParam("configid") int configid)
    {
        _configurationDAO.DeleteConfiguration(configid);

        return "redirect:/configs";
    }
}
