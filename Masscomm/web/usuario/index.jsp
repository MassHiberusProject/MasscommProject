<%-- 
    Document   : index
    Created on : 19-sep-2016, 9:01:33
    Author     : pmayor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setAttribute("isAdmin", request.isUserInRole("administrador"));
    request.setAttribute("username", request.getUserPrincipal().getName().toUpperCase());
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio Usuario</title>
        <meta charset="UTF-8">
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <link href="${ctx}/CSS/custom.css" rel="stylesheet" media="all" type="text/css">
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="navbar.html" %>
        <div class="container">
            <h1 class="col-sm-offset-2 col-sm-8">Bienvenido a la gestión de visitantes y cumpleaños de Masscomm</h1>
        </div>
    </body>
</html>
