package jdbc_application;

import jdbc_application.DAO.ConfigurationDAO;
import jdbc_application.DAO.OrderDAO;
import jdbc_application.DAO.UserDAO;
import jdbc_application.models.Configurations;
import jdbc_application.models.Orders;
import jdbc_application.models.Users;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose the action:\nActions with users - 1\nActions with configurations - 2\nActions with orders - 3");

            int action = Integer.parseInt(scanner.nextLine());

            switch (action){
                case 1:
                    System.out.println("Choose action with user\nView all - 1\nView by id - 2\nCreate - 3\nDelete - 4");
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

                            boolean resultCreate = UserDAO.CreateUser(userName, userSurname, userEmail);

                            if (resultCreate) {
                                System.out.println("User successful created");
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
                                System.out.println("User successful created");
                            }
                            else {
                                System.out.println("User can't be created");
                            }

                            break;
                    }

                    break;

                case 2:
                    System.out.println("Choose action with config\nView all - 1\nView by id - 2\nCreate - 3\nDelete - 4");
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

                            boolean resultCreate = UserDAO.CreateUser(userName, userSurname, userEmail);

                            if (resultCreate) {
                                System.out.println("User successful created");
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
                                System.out.println("User successful created");
                            }
                            else {
                                System.out.println("User can't be created");
                            }

                            break;
                    }

                    break;

                case 3:
                    System.out.println("Choose action with order\nView all - 1\nView by id - 2\nCreate - 3\nDelete - 4");
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

                            boolean resultCreate = UserDAO.CreateUser(userName, userSurname, userEmail);

                            if (resultCreate) {
                                System.out.println("User successful created");
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
                                System.out.println("User successful created");
                            }
                            else {
                                System.out.println("User can't be created");
                            }

                            break;
                    }

                    break;

            }
        }
    }
}