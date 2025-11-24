package jdbc_application.servlets;

import jdbc_application.DAO.UserDAO;
import jdbc_application.models.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import jdbc_application.DAO.UserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

import java.io.IOException;

public class workUserServlet extends HttpServlet{

    private UserDAO _userDAO;

    @Override
    public void init() throws ServletException
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        _userDAO = context.getBean("UserDAO", UserDAO.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");

        _userDAO.CreateUser(name, surname, email);

        List<Users> usersList = _userDAO.GetUsers();

        request.setAttribute("userList", usersList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/user.jsp");
        requestDispatcher.forward(request, response);
    }
}
