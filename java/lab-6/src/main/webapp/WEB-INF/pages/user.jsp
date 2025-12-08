<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html language="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Config page</title>
</head>
<body>
    <p> Все пользователи </p>

    <c:choose>
        <c:when test="${empty userList}">
            <p> Нет ни одного пользователя </p>
        </c:when>
        <c:otherwise>
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
                    <c:forEach items="${userList}" var="user">
                        <tr>
                            <th scope="row"> ${user.getId()} </th>
                            <td> ${user.getName()} </td>
                            <td> ${user.getSurname()} </td>
                            <td> ${user.getEmail()} </td>
                        </tr>
                    </c:forEach>
                </tbody>
            <table>
        </c:otherwise>
    </c:choose>

    <br><br><br>

    <p> Создать нового пользователя: </p>
    <form action="users" method="post">
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

    <br><br><br>

    <form action="users/delete" method="post">
        <label for="userid">Userid:</label>
        <input type="number" id="userid" name="userid"/>

        <br><br>

        <input type="submit" value="Submit"/>
    </form>
</body>
</html>