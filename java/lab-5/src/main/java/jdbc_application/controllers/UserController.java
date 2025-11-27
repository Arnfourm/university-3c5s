package jdbc_application.controllers;

import jdbc_application.DAO.UserDAO;
import jdbc_application.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserDAO _userDAO;

    public UserController(UserDAO userDao)
    {
        _userDAO = userDao;
    }

    @GetMapping("/users")
    public String GetUsers(Model model)
    {
        List<Users> usersList = _userDAO.GetUsers();

        model.addAttribute("userList", usersList);

        return "user";
    }

    @PostMapping("/users")
    public String CreateUser(@ModelAttribute Users user, Model model)
    {
        _userDAO.CreateUser(user.GetName(), user.GetSurname(), user.GetEmail());

        return "user";
    }

    @DeleteMapping("/users")
    public String DeleteUser(@ModelAttribute int userid, Model model)
    {
        _userDAO.DeleteUser(userid);

        return "user";
    }
}
