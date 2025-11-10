package jdbc_application.servlets;

import jdbc_application.DAO.UserDAO;
import jdbc_application.models.Users;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserServlet extends HttpServlet {

    private UserDAO _configDAO;

    @Override
    public void init() throws ServletException
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        _configDAO = context.getBean("UserDAO", UserDAO.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        List<Users> usersList = _configDAO.GetUsers();

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Vps store </title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("</head>");
        out.println("<body>");
        out.println("Все пользователи:");

        out.println("<div>");
        for (Users user : usersList)
        {
            out.println(user.GetId() + " " + user.GetName() + " " + user.GetSurname() + " " + user.GetEmail());
        }
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
