package jdbc_application.DAO;

import jdbc_application.models.Configurations;
import jdbc_application.models.Orders;
import jdbc_application.models.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationDAO {
    private static final String url = "jdbc:postgresql://127.0.0.1/jdbc_lab_3";
    private static final String user = "postgres";
    private static final String pass = "1234";

    public static List<Configurations> GetConfigurations(){
        String sql = "SELECT * FROM CONFIGURATIONS;";
        List<Configurations> configurationsList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass);){
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

    public static Configurations GetConfigurationById(int id){
        String sql = "SELECT * FROM configurations WHERE id = ?";
        Configurations currentConfig = null;

        try (Connection conn = DriverManager.getConnection(url, user, pass)){
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

    public static int CreateConfiguration(String cpu_name, float cpu_ghz, int ram_volume, int disk_volume) {
        String sql = "INSERT INTO configurations(cpu_name, cpu_ghz, ram_volume, disk_volume)" +
                     "VALUES (?, ?, ?, ?)";
        int newConfigId = -1;

        try (Connection conn = DriverManager.getConnection(url, user, pass)){
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

    public static boolean DeleteConfiguration(int id){
        String sql = "DELETE FROM configurations WHERE id = ?";
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
}
