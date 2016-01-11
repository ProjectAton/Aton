<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>University Enrollments</title>
 
    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>
 
</head>
 
 
<body>
    <h2>List of Employees</h2>  
    <table>
        <tr>
            <td>NAME</td><td>Joining Date</td><td>Salary</td><td>SSN</td><td></td>
        </tr>
        <c:forEach items="${equipos}" var="equipo">
            <tr>
            <td>${equipo.mac}</td>
            <td>${equipo.ip}</td>
            <td>${equipo.descripcion}</td>
            <td><a href="<c:url value='/editar-equipo-${equipo.mac}' />">${equipo.mac}</a></td>
            <td><a href="<c:url value='/eliminar-equipo-${equipo.mac}' />">eliminar</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="<c:url value='/nuevo' />">Agregar un nuevo equipo</a>
</body>
</html>