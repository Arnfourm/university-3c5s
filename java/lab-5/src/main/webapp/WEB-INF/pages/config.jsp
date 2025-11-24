<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, models.Configurations" %>

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
    %<
      } else {
    $>
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
                    <th scope="row"> <%= configuration.GetId() %> </th>
                    <td> <%= configuration.GetCpuName() %> </td>
                    <td> <%= configuration.GetCpuGhz() %> </td>
                    <td> <%= configuration.GetDiskVolume() %> </td>
                </tr>
            </tbody>
        <table>
    <$
      }
    %>

</body>
</html>