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
        <title>Añadir usuarios</title>
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
                <h3>Añadir nuevos usuarios</h3>
            </div>
        </div>
        <c:if test="${error !=null}">
            <div class="row">
                <div class="alert alert-danger col-sm-offset-2 col-sm-8" role="alert">
                    <p><c:out value="${error}"/></p>
                </div>
            </div>
        </c:if>
        <div class="row">
            <div id="login_container" class="container">
                <form class="form-horizontal" name="login" id="flogin" action="AnadirUsuario" method="post">
                    <div class="form-group">
                        <label for="inputUser" class="col-sm-4 control-label">Usuario *</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="usr" id="usr" placeholder="Nombre de usuario" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-4 control-label">Contraseña *</label>
                        <div class="col-sm-5">
                            <input type="password" class="form-control" name="pwd" id="pwd" placeholder="Contraseña" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-4 control-label">Correo electrónico *</label>
                        <div class="col-sm-5">
                            <input type="email" class="form-control" name="mail" id="mail" placeholder="Correo electrónico" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputRol" class="col-sm-4 control-label">Rol *</label>
                        <div class="col-sm-5">
                            <label class="radio-inline"><input type="radio" name="rol" id="rol1" checked="true" value="usuario">Usuario</label>
                            <label class="radio-inline"><input type="radio" name="rol" id="rol2" value="administrador">Administrador</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-5">
                            <button type="submit" class="btn btn-primary">Añadir usuario</button>
                            <a href="ListaUsuarios" class="btn btn-primary" role="button">Cancelar</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
