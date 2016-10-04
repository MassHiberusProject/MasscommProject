<%-- 
    Document   : editarVisita
    Created on : 04-oct-2016, 8:19:25
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edición visita</title>
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
                <h4>Editar visita</h4>
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
                <form enctype="multipart/form-data" class="form-horizontal" name="login" id="flogin" action="EditarVisita" method="post">
                    <div class="form-group">
                        <label for="inputNombre" class="col-sm-3 control-label">Nombre *</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" value="${visita.nombre!=null ? visita.nombre : nombre}" required="true" autofocus="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputApellidos" class="col-sm-3 control-label">Apellidos</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="apellidos" id="apellidos" placeholder="Apellidos" value="${visita.apellidos!=null ? visita.apellidos : apellidos}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmpresa" class="col-sm-3 control-label">Empresa</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="empresa" id="empresa" placeholder="Empresa" value="${visita.empresa!=null ? visita.empresa : empresa}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmpresa" class="col-sm-3 control-label">Cargo</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="cargo" id="cargo" placeholder="Cargo en la empresa" value="${visita.cargo!=null ? visita.cargo : cargo}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFecha" class="col-sm-3 control-label">Fecha *</label>
                        <div class="col-sm-6">
                            <input required="true" type="date" class="form-control" name="fecha" id="fecha" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${visita.fecha!=null ? visita.fecha : fech}" />">                   
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputFoto" class="col-sm-3 control-label">Foto</label>
                        <div class="col-sm-4">
                            <input type="file" name="foto" id="foto">
                        </div>
                        <c:if test="${visita.foto!=null}">
                            <a class="col-sm-2 text-right" target="_blank" href="${ctx}/img/${visita.foto}"><span class="glyphicon glyphicon-picture"></span> Foto actual</a>
                        </c:if>
                        <c:if test="${foto_id!=null}">
                            <a class="col-sm-2 text-right" target="_blank" href="${ctx}/img/${foto_id}"><span class="glyphicon glyphicon-picture"></span> Foto actual</a>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="inputFoto" class="col-sm-3 control-label">Logo</label>
                        <div class="col-sm-4">
                            <input type="file" name="logo" id="logo" >
                        </div>
                        <c:if test="${visita.logo!=null}">
                            <a class="col-sm-2 text-right" target="_blank" href="${ctx}/img/${visita.logo}"><span class="glyphicon glyphicon-picture"></span> Logo actual</a>
                        </c:if>
                        <c:if test="${logo_id!=null}">
                            <a class="col-sm-2 text-right" target="_blank" href="${ctx}/img/${logo_id}"><span class="glyphicon glyphicon-picture"></span> Logo actual</a>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                            <button type="submit" class="btn btn-primary">Modificar</button>
                            <a class="btn btn-primary" href="ListaVisitas" role="button">Cancelar</a>
                        </div>
                    </div>
                    <div class="form-group" hidden="true">
                        <input type="text" class="form-control" name="id" id="id" value="<c:out value="${visita.id!=null ? visita.id : id}"/>" hidden="true">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
