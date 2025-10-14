package jdbc_application.DAO;

import jdbc_application.models.Orders;
import jdbc_application.models.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final String url = "jdbc:postgresql://127.0.0.1/jdbc_lab_3";
    private static final String user = "postgres";
    private static final String pass = "1234";

    public static List<Orders> GetOrders(){
        String sql = "SELECT * FROM ORDERS;";
        List<Orders> ordersList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass);){
            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery(sql);

            while (result.next()){
                ordersList.add(new Orders(
                        result.getInt("id"),
                        result.getInt("user_id"),
                        result.getInt("config_id"),
                        result.getFloat("total"),
                        result.getDate("order_date"),
                        result.getTime("order_time")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ordersList;
    }

    public static Orders GetOrderById(int id){
        String sql = "SELECT * FROM ORDERS WHERE id = ?";
        Orders currentOrder = null;

        try (Connection conn = DriverManager.getConnection(url, user, pass)){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                currentOrder = new Orders(
                        result.getInt("id"),
                        result.getInt("user_id"),
                        result.getInt("config_id"),
                        result.getFloat("total"),
                        result.getDate("order_date"),
                        result.getTime("order_time")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentOrder;
    }

    public static int CreateOrder(int user_id, int config_id, float total, Date order_date, Time order_time) {
        String sql = "INSERT INTO ORDERS(user_id, config_id, total, order_date, order_time)" +
                     "VALUES (?, ?, ?, ?, ?)";
        int newOrderId = -1;

        try (Connection conn = DriverManager.getConnection(url, user, pass)){
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, config_id);
            preparedStatement.setFloat(3, total);
            preparedStatement.setDate(4, order_date);
            preparedStatement.setTime(5, order_time);

            try {
                preparedStatement.executeUpdate();
                ResultSet result = preparedStatement.getGeneratedKeys();

                result.next();
                newOrderId = result.getInt(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newOrderId;
    }

    public static boolean DeleteOrder(int id){
        String sql = "DELETE FROM ORDERS WHERE id = ?";
        boolean resultFlag = true;

        try (Connection conn = DriverManager.getConnection(url, user, pass)){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            try {
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                resultFlag = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultFlag = false;
        }

        return resultFlag;
    }

    public static boolean UpdateOrder(int id, String name, String surname, String email){
        String sql = "UPDATE ORDERS" +
                     "SET NAME = ?, SURNAME = ?, EMAIL = ?" +
                     "WHERE id = ?";
        boolean resultFlag = true;

        try (Connection conn = DriverManager.getConnection(url, user, pass)){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            Users currentUser = UserDAO.GetUserById(id);

            if (name != null){
                preparedStatement.setString(1, name);
            } else {
                preparedStatement.setString(1, currentUser.GetName());
            }
            if (surname != null){
                preparedStatement.setString(2, surname);
            } else {
                preparedStatement.setString(2, currentUser.GetSurname());
            }
            if (email != null){
                preparedStatement.setString(3, email);
            } else {
                preparedStatement.setString(3, currentUser.GetEmail());
            }
            preparedStatement.setInt(4, id);

            try {
                preparedStatement.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
                resultFlag = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultFlag = false;
        }

        return resultFlag;
    }
}
