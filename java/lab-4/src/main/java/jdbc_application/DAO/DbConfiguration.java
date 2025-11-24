//package jdbc_application.DAO;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DbConfiguration {
//    private static final String url = "jdbc:postgresql://localhost/postgres";
//    private static final String user = "postgres";
//    private static final String pass = "postgres";
//
//    public Connection GetConnection() throws SQLException {
//        return DriverManager.getConnection(url, user, pass);
//    }
//}