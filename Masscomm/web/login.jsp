<%-- 
    Document   : login
    Created on : 16-sep-2016, 10:55:47
    Author     : pmayor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de sesión</title>
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">
        <link rel="stylesheet" type="text/css" href="CSS/custom.css">
        <link rel="stylesheet" href="CSS/bootstrap.min.css" type="text/css"/>
        <script src="JS/jquery-1.12.4.min.js"></script>
        <script src="JS/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="login_container" class="container">
            <c:if test="${error}">
                <div class="alert alert-danger" role="alert">
                    <p><c:out value="${error}"/></p>
                </div>
            </c:if>
            <form class="form-horizontal" name="login" id="flogin" action="Autenticacion" method="post">
                <div class="form-group">
                    <label for="inputUser" class="col-sm-4 control-label">Usuario</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="usr" id="usr" placeholder="Nombre de usuario" required="true" autofocus="true">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-sm-4 control-label">Contraseña</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" name="pwd" id="pwd" placeholder="Contraseña" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-5">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Recordar los datos
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-5">
                        <button type="submit" class="btn btn-primary">Iniciar sesión</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
