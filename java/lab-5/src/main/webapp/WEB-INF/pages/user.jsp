<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="jdbc_application.models.Users" %>

<!DOCTYPE html>
<html language="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Config page</title>
</head>
<body>
    <p> Все пользователи </p>

    <%
      List<Users> usersList = (List<Users>)request.getAttribute("userList");
      if (usersList.isEmpty()) {
    %>
        <p> Нет ни одного пользователя </p>
    <%
      } else {
    %>
        <table>
            <thead>
                <tr>
                    <th scope="col"> Id пользователя </th>
                    <th scope="col"> Имя </th>
                    <th scope="col"> Фамилия </th>
                    <th scope="col"> Почта </th>
                <tr>
            </thead>

            <tbody>
            <%
                for (Users user : usersList) {
            %>
                <tr>
                    <th scope="row"> <%= user.GetId() %> </th>
                    <td> <%= user.GetName() %> </td>
                    <td> <%= user.GetSurname() %> </td>
                    <td> <%= user.GetEmail() %> </td>
                </tr>
            <%
            }
            %>
            </tbody>
        <table>
    <%
      }
    %>

    <br><br><br>

    <p> Создать нового пользователя: </p>
    <form action="workuser" method=Post>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"/>
        <br><br>

        <label for="surname">Surname:</label>
        <input type="text" id="surname" name="surname"/>
        <br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email">

        <br><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>