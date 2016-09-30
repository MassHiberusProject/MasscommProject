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

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <link href="${ctx}/CSS/customAdmin.css" rel="stylesheet" media="all" type="text/css">
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="navbar.html" %>
        <div class="row">
            <div class="col-sm-offset-1 col-sm-5">
                <h4>Lista de usuarios</h4>
            </div>
        </div>
        <c:if test="${error !=null}">
            <div class="alert alert-danger col-sm-offset-2 col-sm-8" role="alert">
                <p><c:out value="${error}"/></p>
            </div>
        </c:if>
        <c:if test="${msg !=null}">
            <div class="alert alert-success col-sm-offset-2 col-sm-8" role="alert">
                <p><c:out value="${msg}"/></p>
            </div>
        </c:if>
        <div id="listaUsuarios" class="table-responsive col-sm-offset-3 col-sm-6">
            <table class="table table-hover">
                <tr>
                    <th>&nbsp;</th>
                    <th>Nombre de usuario</th>
                    <th>Correo electrónico</th>
                </tr>
                <c:forEach var="usuario" items="${users}">
                    <tr>
                        <td>
                            <a href="<c:url value="EliminarUsuario"><c:param name="user" value="${usuario.id}"/></c:url>" >
                                    <span class="glyphicon glyphicon-remove"></span> Eliminar
                                </a>
                                <a href="<c:url value="EditarUsuario"><c:param name="user" value="${usuario.id}"/></c:url>" >
                                    <span class="glyphicon glyphicon-pencil"></span> Editar
                                </a>
                            </td>
                            <td>${usuario.username}</td>
                        <td>${usuario.email}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
