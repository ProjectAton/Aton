<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registro de un nuevo equipo</title>
 
<style>
 
    .error {
        color: #ff0000;
    }
</style>
 
</head>
 
<body>
 
    <h2>Registration Form</h2>
  
    <form:form method="POST" modelAttribute="equipo">
        <table>
            <tr>
                <td><label for="mac">MAC: </label> </td>
                <td><form:input path="mac" id="mac"/></td>
                <td><form:errors path="mac" cssClass="error"/></td>
            </tr>
         
            <tr>
                <td><label for="descripcion">Descripcion: </label> </td>
                <td><form:input path="descripcion" id="descripcion"/></td>
                <td><form:errors path="descripcion" cssClass="error"/></td>
            </tr>
     
            <tr>
                <td><label for="ip">IP: </label> </td>
                <td><form:input path="ip" id="ip"/></td>
                <td><form:errors path="ip" cssClass="error"/></td>
            </tr>
            
            <tr>
                <td><label for="sala">Sala: </label> </td>
                <td><form:select path="sala" items="${salas}" itemValue="id"/></td>
                <td><form:errors path="sala" cssClass="error"/></td>
            </tr>
     
            <tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Register"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </form:form>
    <br/>
    <br/>
    Go back to <a href="<c:url value='/list' />">List of All Employees</a>
</body>
</html>