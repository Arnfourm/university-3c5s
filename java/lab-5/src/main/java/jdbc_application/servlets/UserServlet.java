//package jdbc_application.servlets;
//
//import jdbc_application.DAO.UserDAO;
//import jdbc_application.models.Users;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.RequestDispatcher;
//
//import java.io.IOException;
//import java.util.List;
//
//public class UserServlet extends HttpServlet {
//
//    private UserDAO _userDAO;
//
//    @Override
//    public void init() throws ServletException
//    {
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        _userDAO = context.getBean("UserDAO", UserDAO.class);
//    }
//
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//    {
//        List<Users> usersList = _userDAO.GetUsers();
//
//        request.setAttribute("userList", usersList);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/user.jsp");
//        requestDispatcher.forward(request, response);
//    }
//}
