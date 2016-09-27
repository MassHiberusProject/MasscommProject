<%-- 
    Document   : configurarCuenta
    Created on : 25-sep-2016, 22:48:59
    Author     : Paula
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Configurar cuenta</title>
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
        <div class="row">
            <div class="col-sm-offset-1 col-sm-5">
                <h4>Configurar cuenta</h4>
            </div>
        </div>
        <div class="row">
            <div id="confuser_container" class="container">
                <c:choose>
                    <c:when test="${error!=null}">
                        <div class="row">
                            <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                                <c:out value="${error}"/>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <form class="form-horizontal" name="login" id="flogin" action="ConfigurarCuenta" method="post">
                            <div class="form-group">
                                <label for="inputUser" class="col-sm-4 control-label">Usuario</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="usr" id="usr" placeholder="Nombre de usuario" autofocus="true" required="true" value="<c:out value="${usuario.username!=null ? usuario.username: usr}"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword" class="col-sm-4 control-label">Contraseña</label>
                                <div class="col-sm-5">
                                    <input type="password" class="form-control" name="pwd" id="pwd" placeholder="Contraseña" value="<c:out value="${pwd}"/>">
                                </div>
                                <div class="row">
                                    <p class="text-info col-sm-offset-4 col-sm-5">La clave debe tener una longitud mayor de 8 y contener letras (al menos una mayúscula y una minúscula) y números</p>
                                </div>
                                <div class="row">
                                    <p class="text-primary col-sm-offset-4 col-sm-5">Si no desea cambiar la contraseña deje el campo vacío</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail" class="col-sm-4 control-label">Correo electrónico</label>
                                <div class="col-sm-5">
                                    <input type="email" class="form-control" name="mail" id="mail" placeholder="Correo electrónico" required="true" value="<c:out value="${usuario.email!=null ? usuario.email : mail}"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-5">
                                    <button type="submit" class="btn btn-primary">Aceptar</button>
                                    <a href="Inicio" class="btn btn-primary" role="button">Cancelar</a>
                                </div>
                            </div>
                            <div class="form-group" hidden="true">
                                <input type="text" class="form-control" name="id" id="id" value="<c:out value="${usuario.id!=null ? usuario.id : id}"/>" hidden="true">
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
