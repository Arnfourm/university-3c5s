//package jdbc_application.servlets;
//
//import jakarta.servlet.ServletException;
//import jdbc_application.DAO.ConfigurationDAO;
//import jdbc_application.models.Configurations;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.RequestDispatcher;
//
//import java.io.IOException;
//import java.util.List;
//
//public class ConfigurationServlet extends HttpServlet {
//
//    private ConfigurationDAO _configDAO;
//
//    @Override
//    public void init() throws ServletException
//    {
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        _configDAO = context.getBean("ConfigurationDAO", ConfigurationDAO.class);
//    }
//
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//    {
//        List<Configurations> configurationsList = _configDAO.GetConfigurations();
//
//        request.setAttribute("configList", configurationsList);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/config.jsp");
//        requestDispatcher.forward(request, response);
//    }
//}
