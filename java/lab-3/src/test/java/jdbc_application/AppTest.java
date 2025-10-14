package jdbc_application;

import jdbc_application.DAO.ConfigurationDAO;
import jdbc_application.DAO.OrderDAO;
import jdbc_application.DAO.UserDAO;
import jdbc_application.models.Configurations;
import jdbc_application.models.Orders;
import jdbc_application.models.Users;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    String userName = "testerovchik";
    String userSurname = "testerov";
    String userEmail = "TestEmail@gmail.com";

    String configCpuName = "TestCpuName";
    float configCpuGhz = 2.1F;
    int configRamVolume = 1321;
    int configDiskVolume = 120;

    int orderUserId = 2;
    int orderConfigId = 1;
    float orderTotal = 321.4F;
    Date orderDate = Date.valueOf("2025-01-23");
    Time orderTime = Time.valueOf("23:03:41");

    @Test
    public void testCreateUser() {
        int newUserId = UserDAO.CreateUser(userName, userSurname, userEmail);

        Users currentUser = UserDAO.GetUserById(newUserId);

        assertEquals(currentUser.GetName(), userName);
        assertEquals(currentUser.GetSurname(), userSurname);
        assertEquals(currentUser.GetEmail(), userEmail);

        boolean result = UserDAO.DeleteUser(newUserId);
        assertTrue(result);
    }

    @Test
    public void testCreateConfig(){
        int newConfigId = ConfigurationDAO.CreateConfiguration(configCpuName, configCpuGhz, configRamVolume, configDiskVolume);

        Configurations currentConfig = ConfigurationDAO.GetConfigurationById(newConfigId);

        assertEquals(currentConfig.GetCpuName(), configCpuName);
        assertEquals(currentConfig.GetCpuGhz(), configCpuGhz);
        assertEquals(currentConfig.GetRamVolume(), configRamVolume);
        assertEquals(currentConfig.GetDiskVolume(), configDiskVolume);

        boolean result = ConfigurationDAO.DeleteConfiguration(newConfigId);
        assertTrue(result);
    }

    @Test
    public void testCreateOrder(){
        int newOrderId = OrderDAO.CreateOrder(orderUserId, orderConfigId, orderTotal, orderDate, orderTime);

        Orders currentOrder = OrderDAO.GetOrderById(newOrderId);

        assertEquals(currentOrder.GetUserId(), orderUserId);
        assertEquals(currentOrder.GetConfigId(), orderConfigId);
        assertEquals(currentOrder.GetTotal(), orderTotal);
        assertEquals(currentOrder.GetOrderDate(), orderDate);
        assertEquals(currentOrder.GetOrderTime(), orderTime);

        boolean result = OrderDAO.DeleteOrder(newOrderId);
        assertTrue(result);
    }
}
