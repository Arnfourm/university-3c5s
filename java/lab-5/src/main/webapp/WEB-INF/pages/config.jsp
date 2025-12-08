<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="jdbc_application.models.Configurations" %>

<!DOCTYPE html>
<html language="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Config page</title>
</head>
<body>
    <p> Все конфигурации </p>

    <%
      List<Configurations> configurationsList = (List<Configurations>)request.getAttribute("configList");
      if (configurationsList.isEmpty()) {
    %>
        <p> Нет ни одной конфигурации </p>
    <%
      } else {
    %>
        <table>
            <thead>
                <tr>
                    <th scope="col"> Id конф </th>
                    <th scope="col"> Cpu название </th>
                    <th scope="col"> Cpu частота </th>
                    <th scope="col"> Дисковый объем </th>
                <tr>
            </thead>

            <tbody>
            <%
                for (Configurations configuration : configurationsList) {
            %>
                <tr>
                    <th scope="row"> <%= configuration.getId() %> </th>
                    <td> <%= configuration.getCpuName() %> </td>
                    <td> <%= configuration.getCpuGhz() %> </td>
                    <td> <%= configuration.getDiskVolume() %> </td>
                </tr>
            <%
            }
            %>
            </tbody>
        <table>
    <%
      }
    %>

    <p> Создать новую конфигурацию: </p>
    <form action="configs" method="post">
        <label for="cpuName">Cpuname:</label>
        <input type="text" id="cpuName" name="cpuName"/>
        <br><br>

        <label for="cpuGhz">Cpu ghz:</label>
        <input type="number" step="0.01" id="cpuGhz" name="cpuGhz"/>
        <br><br>

        <label for="ramVolume">Ram volume:</label>
        <input type="number" id="ramVolume" name="ramVolume">
        <br><br>

        <label for="diskVolume">Disk volume:</label>
        <input type="number" id="diskVolume" name="diskVolume">
        <br><br>

        <input type="submit" value="Submit">
    </form>

    <br><br><br>

    <form action="configs/delete" method="post">
        <label for="configid">Config id:</label>
        <input type="number" id="configid" name="configid"/>

        <br><br>

        <input type="submit" value="Submit"/>
    </form>

</body>
</html>