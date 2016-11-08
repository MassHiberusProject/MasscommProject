<%-- 
    Document   : anadirvisita
    Created on : 03-oct-2016, 9:58:36
    Author     : pmayor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    HttpSession misession = (HttpSession) request.getSession();
    String usuario = (String) misession.getAttribute("username");
    request.setAttribute("isAdmin", request.isUserInRole("administrador"));
    if (usuario == null) {
        request.setAttribute("username", request.getUserPrincipal().getName().toUpperCase());
    } else {
        request.setAttribute("username", usuario.toUpperCase());
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Añadir visita</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
        <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
    </head>
    <body>
        <%@include file="navbarVisitas.html" %>
        <div class="row">
            <div class="col-sm-offset-1 col-sm-5">
                <h4>Añadir nuevo visitante</h4>
            </div>
        </div>
        <div class="row">
            <div class="container">
                <c:if test="${contador!=0}">
                    <div class="row">
                        <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                            <ul>
                                <c:if test="${error_foto !=null}">
                                    <li><c:out value="${error_foto}"/></li>
                                    </c:if>
                                    <c:if test="${error_logo !=null}">
                                    <li><c:out value="${error_logo}"/></li>
                                    </c:if>
                                    <c:if test="${error_insert !=null}">
                                    <li><c:out value="${error_insert}"/></li>
                                    </c:if>
                            </ul>
                        </div>  
                    </div>
                </c:if>
                <form enctype="multipart/form-data" class="form-horizontal" name="login" id="flogin" action="AnadirVisita" method="post">
                    <div class="form-group">
                        <label for="inputNombre" class="col-sm-3 control-label">Nombre *</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" value="<c:out value="${nombre}"/>" required="true" autofocus="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputApellidos" class="col-sm-3 control-label">Apellidos</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="apellidos" id="apellidos" placeholder="Apellidos" value="<c:out value="${apellidos}"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmpresa" class="col-sm-3 control-label">Empresa</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="empresa" id="empresa" placeholder="Empresa" value="<c:out value="${empresa}"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmpresa" class="col-sm-3 control-label">Cargo</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="cargo" id="cargo" placeholder="Cargo en la empresa" value="<c:out value="${cargo}"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFecha" class="col-sm-3 control-label">Fecha *</label>
                        <div class="col-sm-6">
                            <input required="true" type="date" class="form-control" name="fecha" id="fecha" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${f}" />">                   
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputFoto" class="col-sm-3 control-label">Foto</label>
                        <div class="col-sm-6">
                            <input type="file" name="foto" id="foto">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFoto" class="col-sm-3 control-label">Logo</label>
                        <div class="col-sm-6">
                            <input type="file" name="logo" id="logo" >
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                            <button type="submit" class="btn btn-primary">Guardar</button>
                            <a class="btn btn-primary" href="ListaVisitas" role="button">Cancelar</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
