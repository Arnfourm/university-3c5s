package jdbc_application.servlets;

import jakarta.servlet.ServletException;
import jdbc_application.DAO.ConfigurationDAO;
import jdbc_application.models.Configurations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ConfigurationServlet extends HttpServlet {

    private ConfigurationDAO _configDAO;

    @Override
    public void init() throws ServletException
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        _configDAO = context.getBean("ConfigurationDAO", ConfigurationDAO.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        List<Configurations> configurationsList = _configDAO.GetConfigurations();

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Vps store </title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("</head>");
        out.println("<body>");
        out.println("Все конфигурации:");

        out.println("<div>");
        out.println("Configurations count:" + configurationsList.size());
        for (Configurations configuration : configurationsList)
        {
            out.println("тест");
            out.println(configuration.GetId() + " " + configuration.GetCpuName() + " " + configuration.GetCpuGhz() + " " + configuration.GetDiskVolume());
        }
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
