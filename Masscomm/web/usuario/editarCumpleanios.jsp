
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
        <title>Editar cumpleaños</title>
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
        <script>
            $(function () {
                $("#fecha").datepicker({dateFormat: 'yy-mm-dd'});
            });
        </script>
    </head>
    <body>
        <%@include file="navbarCumpleanios.html" %>
        <div class="row">
            <div class="col-sm-offset-1 col-sm-5">
                <h4>Editar cumpleaños</h4>
            </div>
        </div>
        <div class="row">
            <div class="container">
                <c:choose>
                    <c:when test="${error!=null}">
                        <div class="row">
                            <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                                <c:out value="${error}"/>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${contador!=0}">
                            <div class="row">
                                <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                                    <ul>
                                        <c:if test="${error_foto !=null}">
                                            <li><c:out value="${error_foto}"/></li>
                                            </c:if>
                                            <c:if test="${error_insert !=null}">
                                            <li><c:out value="${error_insert}"/></li>
                                            </c:if>
                                    </ul>
                                </div>  
                            </div>
                        </c:if>
                        <form enctype="multipart/form-data" class="form-horizontal" name="login" id="flogin" action="EditarCumpleanios" method="post">
                            <div class="form-group">
                                <label for="inputNombre" class="col-sm-3 control-label">Nombre *</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" value="${cumpleanios.nombre!=null ? cumpleanios.nombre : nombre}" required="true" autofocus="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputApellidos" class="col-sm-3 control-label">Apellidos</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="apellidos" id="apellidos" placeholder="Apellidos" value="${cumpleanios.apellidos!=null ? cumpleanios.apellidos : apellidos}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmpresa" class="col-sm-3 control-label">Empresa</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="empresa" id="empresa" placeholder="Empresa" value="${cumpleanios.empresa!=null ? cumpleanios.empresa : empresa}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputFecha" class="col-sm-3 control-label">Fecha *</label>
                                <div class="col-sm-6">
                                    <input required="true" type="text" class="form-control" name="fecha" id="fecha" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${cumpleanios.fecha!=null ? cumpleanios.fecha : fech}" />">                  
                                </div>              
                            </div>
                            <div class="form-group">
                                <label for="inputFoto" class="col-sm-3 control-label">Foto</label>
                                <div class="col-sm-4">
                                    <input type="file" name="foto" id="foto">
                                </div>
                                <c:if test="${cumpleanios.imagen!=null}">
                                    <a class="col-sm-2 text-right" target="_blank" href="${ctx}/img/${cumpleanios.imagen}"><span class="glyphicon glyphicon-picture"></span> Foto actual</a>
                                </c:if> 
                                <c:if test="${foto_id!=null}">
                                    <a class="col-sm-2 text-right" target="_blank" href="${ctx}/img/${foto_id}"><span class="glyphicon glyphicon-picture"></span> Foto actual</a>
                                </c:if>
                            </div>                       
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-5">
                                    <button type="submit" class="btn btn-primary">Modificar</button>
                                    <a class="btn btn-primary" href="ListaCumpleanios" role="button">Cancelar</a>
                                </div>
                            </div>
                            <div class="form-group" hidden="true">
                                <input type="text" class="form-control" name="id" id="id" value="<c:out value="${cumpleanios.id!=null ? cumpleanios.id : id}"/>" hidden="true">
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
