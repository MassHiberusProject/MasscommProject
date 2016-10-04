<%-- 
    Document   : cumpleanios
    Created on : 19-sep-2016, 13:17:32
    Author     : claencina
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
        <title>Lista de usuarios</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/bootstrap.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                var cont = 0;
                var cod = "";
                var fech = "";
                var d = new Date();
                var codigos = [];

                $("#bt_rss").click(function () {
                    $("#id_tr input").each(function (index)
                    {
                        if ($(this).is(':checked')) {
                            cod = $(this).val();
                            var codigo = cod.split("+");
                            cont++;
                            codigos.push(codigo[0]);
                        }
                    })
                    if (cont == 0)
                    {
                        $("#id_tr input").each(function (index)
                        {
                            cod = $(this).val();
                            var codi = cod.split("+");
                            var codigo = codi[0];
                            var fech = codi[1]
                            var fec = fech.split("-");

                            var dia = fec[2];
                            var mes = fec[1];

                            if (dia == d.getDate() && mes == (d.getMonth() + 1)) {
                                codigos.push(codigo);
                            }
                        })
                    }
                    $.ajax("GenerarRSS", {
                        data: {'ids': codigos.toLocaleString()},
                        dataType: 'json',
                        success: rss,
                        error: function (resp, msg, ex) {
                            alert("Error (" + resp.status + "):" + msg);
                        }
                    });
                });
                function rss(data, msg, resp) {
                    alert("llega");
                }
            });

        </script>
    </head>
    <body>
        <%@include file="navbar.html" %>
        <div class="container">
            <c:if test="${msg !=null}">
                <div class="row">
                    <div class="alert alert-success col-sm-offset-2 col-sm-8" role="alert">
                        <p><c:out value="${msg}"/></p>
                    </div>
                </div>
            </c:if>
            <a class="btn btn-primary" href="AnadirCumpleanios" role="button" style="float: right">Nuevo cumpleaños</a>
            <br><br><br>

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
                            <tr id="id_tr">
                                <td hidden="true">${cumple.id} </td>
                                <td>    
                                    <a href="<c:url value="EditarCumpleanios"><c:param name="id" value="${cumple.id}"/></c:url>"><span class="glyphicon glyphicon-pencil"></span></a>
                                    <a href="<c:url value="EliminarCumpleanios"><c:param name="id" value="${cumple.id}"/></c:url>"><span class="glyphicon glyphicon-remove"></span></a>
                                    </td>
                                    <td>${cumple.nombre} </td>
                                <td>${cumple.apellidos} </td>
                                <td>${cumple.empresa} </td>
                                <td> <fmt:formatDate pattern="dd 'de' MMMM" value="${cumple.fecha}" /></td>
                                <td><c:if test="${cumple.imagen!=null}"><a target="_blank" href="${ctx}/img/${cumple.imagen}">Foto</a> </c:if> </td>                          
                                <td> <input type="checkbox" value="${cumple.id} + ${cumple.fecha}"></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a id="bt_rss" class="btn btn-primary" href="" role="button">Generar RSS</a>
            <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" <c:if test="${error_foto!=null}">aria-expanded="true"</c:if> <c:if test="${error_foto!=null}">aria-expanded="false"</c:if>aria-controls="collapseExample">
                    Añadir fondo
                </a>
                    <div <c:if test="${error_foto!=null}"> class="collapse in" </c:if> <c:if test="${error_foto==null}"> class="collapse" </c:if> id="collapseExample" <c:if test="${error_foto!=null}"> aria-expended="true" </c:if>>
                    <br><br>
                <c:if test="${error_foto!=null}">
                    <div class="alert alert-danger">
                        ${error_foto}
                    </div>  
                </c:if>
                <form enctype="multipart/form-data" class="form-horizontal" name="login" id="flogin" action="AnadirFondo" method="post" style="margin-left: 15px;">
                    <div class="form-group">
                        <label for="inputFondo">Fondo</label>
                        <c:if test="${fondo!=null}">
                            <a target="_blank" href="${ctx}/fondo/${fondo}">Fondo</a>
                        </c:if> 
                        <input type="file" name="fondo" id="fondo">
                    </div>
                    <button type="submit" class="btn btn-primary">Guardar fondo</button>
                </form>
            </div>
        </div>
    </body>
</html>
