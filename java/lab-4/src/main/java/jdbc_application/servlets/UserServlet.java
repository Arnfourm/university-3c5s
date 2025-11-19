package jdbc_application.servlets;

import jdbc_application.DAO.UserDAO;
import jdbc_application.models.Configurations;
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

        if (usersList.isEmpty())
        {
            out.println("Нет ни одного пользователя");
        } else {
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th scope=\"col\"> Id пользователя </th>");
            out.println("<th scope=\"col\"> Имя </th>");
            out.println("<th scope=\"col\"> Фамилия </th>");
            out.println("<th scope=\"col\"> Почта </th>");
            out.println("</tr>");
            out.println("</thead>");

            out.println("<tbody>");
            for (Users user : usersList)
            {
                out.println("<tr>");
                out.println("<th scope=\"row\">" + user.GetId() + "</th>");
                out.println("<td>" + user.GetName() + "</tc>");
                out.println("<td>" + user.GetSurname() + "</tc>");
                out.println("<td>" + user.GetEmail()                                       + "</tc>");
                out.println("</tr>");
            }
            out.println("</tbody>");

            out.println("</table>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
