package jdbc_application;

import jdbc_application.DAO.ConfigurationDAO;
import jdbc_application.DAO.OrderDAO;
import jdbc_application.DAO.UserDAO;
import jdbc_application.models.Configurations;
import jdbc_application.models.Orders;
import jdbc_application.models.Users;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------\nChoose the action:\nActions with users - 1\nActions with configurations - 2\nActions with orders - 3");

            int action = Integer.parseInt(scanner.nextLine());

            switch (action){
                case 1:
                    System.out.println("-------\nChoose action with user\nView all - 1\nView by id - 2\nCreate - 3\nDelete - 4");
                    int userAction = Integer.parseInt(scanner.nextLine());

                    switch (userAction){
                        case 1:
                            List<Users> usersList = UserDAO.GetUsers();

                            for (Users user : usersList) {
                                System.out.println(
                                        user.GetId() + " " +
                                        user.GetName() + " " +
                                        user.GetSurname() + " " +
                                        user.GetEmail()
                                );
                            }

                            break;

                        case 2:
                            System.out.println("Write user id");
                            int useridView = Integer.parseInt(scanner.nextLine());

                            Users user = UserDAO.GetUserById(useridView);

                            System.out.println(
                                    user.GetId() + " " +
                                    user.GetName() + " " +
                                    user.GetSurname() + " " +
                                    user.GetEmail()
                            );

                            break;

                        case 3:
                            System.out.println("Write user name, surname, email");
                            String userName = scanner.nextLine();
                            String userSurname = scanner.nextLine();
                            String userEmail = scanner.nextLine();

                            int resultCreate = UserDAO.CreateUser(userName, userSurname, userEmail);

                            if (resultCreate != -1) {
                                System.out.println("User successful created, id: " + resultCreate);
                            }
                            else {
                                System.out.println("User can't be created");
                            }

                            break;

                        case 4:
                            System.out.println("Write user id");
                            int useridDelete = Integer.parseInt(scanner.nextLine());

                            boolean resultDelete = UserDAO.DeleteUser(useridDelete);

                            if (resultDelete) {
                                System.out.println("User successful deleted");
                            }
                            else {
                                System.out.println("User can't be delete");
                            }

                            break;
                    }

                    break;

                case 2:
                    System.out.println("-------\nChoose action with config\nView all - 1\nView by id - 2\nCreate - 3\nDelete - 4");
                    int configAction = Integer.parseInt(scanner.nextLine());

                    switch (configAction){
                        case 1:
                            List<Configurations> configsList = ConfigurationDAO.GetConfigurations();

                            for (Configurations config : configsList) {
                                System.out.println(
                                        config.GetId() + " " +
                                        config.GetCpuName() + " " +
                                        config.GetCpuGhz() + " " +
                                        config.GetRamVolume() + " " +
                                        config.GetDiskVolume()
                                );
                            }

                            break;

                        case 2:
                            System.out.println("Write config id");
                            int configIdView = Integer.parseInt(scanner.nextLine());

                            Configurations configuration = ConfigurationDAO.GetConfigurationById(configIdView);

                            System.out.println(
                                    configuration.GetId() + " " +
                                    configuration.GetCpuName() + " " +
                                    configuration.GetCpuGhz() + " " +
                                    configuration.GetRamVolume() + " " +
                                    configuration.GetDiskVolume()
                            );

                            break;

                        case 3:
                            System.out.println("Write config cpu_name, cpu_ghz, ram_volume, disk_volume");
                            String configCpuName = scanner.nextLine();
                            float configCpuGhz = Float.parseFloat(scanner.nextLine());
                            int configRamVolume = Integer.parseInt(scanner.nextLine());
                            int configDiskVolume = Integer.parseInt(scanner.nextLine());

                            int resultCreate = ConfigurationDAO.CreateConfiguration(configCpuName, configCpuGhz, configRamVolume, configDiskVolume);

                            if (resultCreate != -1) {
                                System.out.println("Config successful created, id: " + resultCreate);
                            }
                            else {
                                System.out.println("Config can't be created");
                            }

                            break;

                        case 4:
                            System.out.println("Write config id");
                            int configIdDelete = Integer.parseInt(scanner.nextLine());

                            boolean resultDelete = ConfigurationDAO.DeleteConfiguration(configIdDelete);

                            if (resultDelete) {
                                System.out.println("Config successful deleted");
                            }
                            else {
                                System.out.println("Config can't be create");
                            }

                            break;
                    }

                    break;

                case 3:
                    System.out.println("-------\nChoose action with order\nView all - 1\nView by id - 2\nCreate - 3\nDelete - 4");
                    int deleteAction = Integer.parseInt(scanner.nextLine());

                    switch (deleteAction){
                        case 1:
                            List<Orders> ordersList = OrderDAO.GetOrders();

                            for (Orders order : ordersList) {
                                int currentUserId = order.GetUserId();
                                int currentConfigId = order.GetConfigId();

                                System.out.println(
                                        order.GetId() + " " +
                                        currentUserId + "(" + UserDAO.GetUserById(currentUserId).GetName() + ") " +
                                        currentConfigId + " " +
                                        order.GetTotal() + " " +
                                        order.GetOrderDate() + " " +
                                        order.GetOrderTime()
                                );
                            }

                            break;

                        case 2:
                            System.out.println("Write order id");
                            int orderIdView = Integer.parseInt(scanner.nextLine());

                            Orders order = OrderDAO.GetOrderById(orderIdView);

                            int currentUserId = order.GetUserId();
                            int currentConfigId = order.GetConfigId();

                            System.out.println(
                                    order.GetId() + " " +
                                    currentUserId + "(" + UserDAO.GetUserById(currentUserId).GetName() + ") " +
                                    currentConfigId + "(" + ConfigurationDAO.GetConfigurationById(currentConfigId) + ") " +
                                    order.GetTotal() + " " +
                                    order.GetOrderDate() + " " +
                                    order.GetOrderTime()
                            );

                            break;

                        case 3:
                            System.out.println("Write order user id, config id, total");
                            int user_id = Integer.parseInt(scanner.nextLine());
                            int config_id = Integer.parseInt(scanner.nextLine());
                            float total = Float.parseFloat(scanner.nextLine());
                            Date order_date = new Date(System.currentTimeMillis());
                            Time order_time = new Time(System.currentTimeMillis());


                            int resultCreate = OrderDAO.CreateOrder(user_id, config_id, total, order_date, order_time);

                            if (resultCreate != -1) {
                                System.out.println("Order successful created, id: " + resultCreate);
                            }
                            else {
                                System.out.println("Order can't be create");
                            }

                            break;

                        case 4:
                            System.out.println("Write order id");
                            int orderIdDelete = Integer.parseInt(scanner.nextLine());

                            boolean resultDelete = OrderDAO.DeleteOrder(orderIdDelete);

                            if (resultDelete) {
                                System.out.println("Order successful created");
                            }
                            else {
                                System.out.println("Order can't be created");
                            }

                            break;
                    }

                    break;

            }
        }
    }
}