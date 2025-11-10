package jdbc_application.DAO;

import jdbc_application.models.Configurations;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationDAO {

    private final DataSource _dbContext;

    public ConfigurationDAO(DataSource dbContext)
    {
        _dbContext = dbContext;
    }

    public List<Configurations> GetConfigurations()
    {
        String sql = "SELECT * FROM CONFIGURATIONS;";
        List<Configurations> configurationsList = new ArrayList<>();

        try (Connection conn = _dbContext.getConnection()){
            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery(sql);

            while (result.next()){

                configurationsList.add(new Configurations(
                        result.getInt("id"),
                        result.getString("cpu_name"),
                        result.getFloat("cpu_ghz"),
                        result.getInt("ram_volume"),
                        result.getInt("disk_volume")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return configurationsList;
    }

    public Configurations GetConfigurationById(int id)
    {
        String sql = "SELECT * FROM configurations WHERE id = ?";
        Configurations currentConfig = null;

        try (Connection conn = _dbContext.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                currentConfig = new Configurations(
                        result.getInt("id"),
                        result.getString("cpu_name"),
                        result.getFloat("cpu_ghz"),
                        result.getInt("ram_volume"),
                        result.getInt("disk_volume")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentConfig;
    }

    public int CreateConfiguration(String cpu_name, float cpu_ghz, int ram_volume, int disk_volume)
    {
        String sql = "INSERT INTO configurations(cpu_name, cpu_ghz, ram_volume, disk_volume)" +
                     "VALUES (?, ?, ?, ?)";
        int newConfigId = -1;

        try (Connection conn = _dbContext.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cpu_name);
            preparedStatement.setFloat(2, cpu_ghz);
            preparedStatement.setInt(3, ram_volume);
            preparedStatement.setInt(4, disk_volume);

            try {
                preparedStatement.executeUpdate();
                ResultSet result = preparedStatement.getGeneratedKeys();

                result.next();
                newConfigId = result.getInt(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newConfigId;
    }

    public boolean DeleteConfiguration(int id)
    {
        String sql = "DELETE FROM configurations WHERE id = ?";
        boolean resultFlag = true;

        try (Connection conn = _dbContext.getConnection()){
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

//    public boolean UpdateConfiguration(int id, String name, String surname, String email){
//        String sql = "UPDATE CONFIGURATIONS" +
//                     "SET NAME = ?, SURNAME = ?, EMAIL = ?" +
//                     "WHERE id = ?";
//        boolean resultFlag = true;
//
//        try (Connection conn = _dbContext.GetConnection()){
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            Users currentUser = UserDAO.GetUserById(id);
//
//            if (name != null){
//                preparedStatement.setString(1, name);
//            } else {
//                preparedStatement.setString(1, currentUser.GetName());
//            }
//            if (surname != null){
//                preparedStatement.setString(2, surname);
//            } else {
//                preparedStatement.setString(2, currentUser.GetSurname());
//            }
//            if (email != null){
//                preparedStatement.setString(3, email);
//            } else {
//                preparedStatement.setString(3, currentUser.GetEmail());
//            }
//            preparedStatement.setInt(4, id);
//
//            try {
//                preparedStatement.executeQuery();
//            } catch (Exception e) {
//                e.printStackTrace();
//                resultFlag = false;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            resultFlag = false;
//        }
//
//        return resultFlag;
//    }
}
