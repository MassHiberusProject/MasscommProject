<%-- 
    Document   : listaUser
    Created on : 20-sep-2016, 10:43:31
    Author     : pmayor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de usuarios</title>
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">
        <link rel="stylesheet" type="text/css" href="../CSS/custom.css">
        <link rel="stylesheet" href="../CSS/bootstrap.min.css" type="text/css"/>
        <script src="../JS/jquery-1.12.4.min.js"></script>
        <script src="../JS/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="navbarAdmin.html" %>
        <div id="listaUsuarios" class="table-responsive col-sm-offset-3 col-sm-6">
            <table class="table table-hover">
                <tr>
                    <th class="col-sm-3">&nbsp;</th>
                    <th>Nombre de usuario</th>
                </tr>
                <c:forEach var="usuario" items="${users}">
                    <tr>
                        <td>
                            <a href="<c:url value="EliminarUsuario"><c:param name="id" value="${usuario.id}"/></c:url>" >
                                <span class="glyphicon glyphicon-remove"></span> Eliminar
                            </a>
                        </td>
                        <td>${usuario.user}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
