<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html language="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Config page</title>
</head>
<body>
    <p> Все конфигурации </p>
    
    <c:choose>
        <c:when test="${empty configList}">
            <p> Нет ни одной конфигурации </p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th scope="col"> Id конф </th>
                        <th scope="col"> Cpu название </th>
                        <th scope="col"> Cpu частота </th>
                        <th scope="col"> Дисковый объем </th>
                        <th scope="col"> Цена </th>
                    <tr>
                </thead>

                <tbody>
                    <c:set var="totalPrice" value="0" scope="page"/>
                    <c:forEach items="${configList}" var="configuration">
                        <tr>
                            <th scope="row"> ${configuration.getId()} </th>
                            <td> ${configuration.getCpuName()} </td>
                            <td> ${configuration.getCpuGhz()} </td>
                            <td> ${configuration.getDiskVolume()} </td>
                            <c:set var="totalPrice" value="${totalPrice + configuration.getPrice()}" scope="page" />
                            <td>
                                <fmt:formatNumber value="${configuration.getPrice()}" type="number" maxFractionDigits="2"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

                <tfoot>
                    <tr>
                        <th scope="row"> Общее </th>
                        <td> </td>
                        <td> </td>
                        <td> </td>
                        <td> ${totalPrice} </td>
                    </tr>
                </tfoot>
            <table>
        </c:otherwise>
    </c:choose>

    <br><br>

    <p> Создать новую конфигурацию: </p>
    <form:form modelAttribute="config" action="configs" method="post">

        <form:label path="cpuName"> Cpu name: </form:label>
        <form:input type="text" path="cpuName" />
        <br>

        <form:label path="cpuGhz"> Cpu ghz: </form:label>
        <form:input type="number" step="0.01" path="cpuGhz" />
        <br>

        <form:label path="ramVolume"> Ram volume: </form:label>
        <form:input type="number" path="ramVolume" />
        <br>

        <form:label path="diskVolume"> Disk volume: </form:label>
        <form:input type="number" path="diskVolume" />
        <br>

        <form:label path="price"> Price: </form:label>
        <form:input type="number" step="0.01" path="price" />   
        <br>

        <input type="submit" value="Send" />

    </form:form>

    <br><br>

    <form action="configs/delete" method="post">
        <label for="configid">Config id:</label>
        <input type="number" id="configid" name="configid"/>

        <br><br>

        <input type="submit" value="Submit"/>
    </form>

</body>
</html>