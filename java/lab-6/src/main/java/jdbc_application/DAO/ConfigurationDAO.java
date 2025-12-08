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
                        result.getInt("disk_volume"),
                        result.getDouble("price")
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
                        result.getInt("disk_volume"),
                        result.getDouble("price")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentConfig;
    }

    public int CreateConfiguration(Configurations newConfig)
    {
        String sql = "INSERT INTO configurations(cpu_name, cpu_ghz, ram_volume, disk_volume, price)" +
                     "VALUES (?, ?, ?, ?, ?)";
        int newConfigId = -1;

        try (Connection conn = _dbContext.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newConfig.getCpuName());
            preparedStatement.setFloat(2, newConfig.getCpuGhz());
            preparedStatement.setInt(3, newConfig.getRamVolume());
            preparedStatement.setInt(4, newConfig.getDiskVolume());
            preparedStatement.setDouble(5, newConfig.getPrice());

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

    public void DeleteConfiguration(int id)
    {
        String sql = "DELETE FROM configurations WHERE id = ?";

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
}