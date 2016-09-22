<%-- 
    Document   : cumpleanios
    Created on : 19-sep-2016, 13:17:32
    Author     : claencina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">
        <link rel="stylesheet" href="CSS/bootstrap.min.css" type="text/css"/>
        <script src="JS/jquery-1.12.4.min.js"></script>
        <script src="JS/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="navbar.html" %>
        <div class="container">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th hidden="true">id</th>
                            <th></th>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>Empresa</th>
                            <th>Fecha</th>
                            <th>Foto</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cumpleanios}" var="cumple" varStatus="i"> 
                            <tr>

                                <td hidden="true">${cumple.id} </td>
                                <td>    
                                    <a href="<c:url value="EditarCumpleanios"><c:param name="id" value="${cumple.id}"/></c:url>"><span class="glyphicon glyphicon-pencil"></span>

                                    <a href="<c:url value="EliminarCumpleanios"><c:param name="id" value="${cumple.id}"/></c:url>"><span class="glyphicon glyphicon-remove"></span></a>

                                </td>
                                <td>${cumple.nombre} </td>
                                <td>${cumple.apellidos} </td>
                                <td>${cumple.empresa} </td>
                                <td> <fmt:formatDate pattern="dd 'de' MMMM" value="${cumple.fecha}" /></td>
                                <td>${cumple.foto} </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
