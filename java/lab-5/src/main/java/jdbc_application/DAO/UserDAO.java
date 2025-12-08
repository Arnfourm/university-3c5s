package jdbc_application.DAO;

import jdbc_application.models.Users;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final DataSource _dbContext;

    public UserDAO(DataSource dbContext)
    {
        _dbContext = dbContext;
    }

    public List<Users> GetUsers(){
        String sql = "SELECT * FROM USERS;";
        List<Users> usersList = new ArrayList<>();

        try (Connection conn = _dbContext.getConnection()){
            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery(sql);

            while (result.next()){
                usersList.add(new Users(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("surname"),
                    result.getString("email")
                ));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return usersList;
    }

    public Users GetUserById(int id){
        String sql = "SELECT * FROM USERS WHERE id = ?";
        Users currentUser = null;

        try (Connection conn = _dbContext.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                currentUser = new Users(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("surname"),
                    result.getString("email")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentUser;
    }

    public int CreateUser(String name, String surname, String email) {
        String sql = "INSERT INTO USERS(name, surname, email)" +
                     "VALUES (?, ?, ?)";
        int newUserId = -1;

        try (Connection conn = _dbContext.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, email);

            try {
                preparedStatement.executeUpdate();
                ResultSet result = preparedStatement.getGeneratedKeys();

                result.next();
                newUserId = result.getInt(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newUserId;
    }

    public void DeleteUser(int id){
        String sql = "DELETE FROM USERS WHERE id = ?";

        try (Connection conn = _dbContext.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            try {
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteUserByEmail(String email)
    {
        String sql = "DELETE FROM users WHERE email = ?";

        try (Connection conn = _dbContext.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);

            try {
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean UpdateUser(int id, String name, String surname, String email){
        String sql = "UPDATE USERS" +
                     "SET NAME = ?, SURNAME = ?, EMAIL = ?" +
                     "WHERE id = ?";
        boolean resultFlag = true;

        try (Connection conn = _dbContext.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            Users currentUser = GetUserById(id);

            if (name != null){
                preparedStatement.setString(1, name);
            } else {
                preparedStatement.setString(1, currentUser.getName());
            }
            if (surname != null){
                preparedStatement.setString(2, surname);
            } else {
                preparedStatement.setString(2, currentUser.getSurname());
            }
            if (email != null){
                preparedStatement.setString(3, email);
            } else {
                preparedStatement.setString(3, currentUser.getEmail());
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
