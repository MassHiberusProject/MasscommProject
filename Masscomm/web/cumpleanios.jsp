<%-- 
    Document   : cumpleanios
    Created on : 19-sep-2016, 13:17:32
    Author     : claencina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">
        <link rel="stylesheet" href="CSS/bootstrap.min.css" type="text/css"/>
        <script src="JS/jquery-1.12.4.min.js"></script>
        <script src="JS/bootstrap.min.js"></script>
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
                    alert(codigos);
                    $.ajax("GenerarRSS", {
                        data: {'ids': codigos.toLocaleString()},
                                dataType: 'json',
                                error: function (resp, msg, ex) {
                                alert("Error (" + resp.status + "):" + msg);
                                }
                    });
                });
            });
            
        </script>
    </head>
    <body>
        <%@include file="navbar.html" %>
        <div class="container">
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
                                <td><c:if test="${cumple.image_id.image_id!=null}"><a target="_blank" href="<c:url value="Im"><c:param name="id" value="${cumple.image_id.image_id}"/></c:url>">Foto</a> </c:if> </td>                          
                                <td> <input type="checkbox" value="${cumple.id} + ${cumple.fecha}"></label</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a id="bt_rss" class="btn btn-primary" href="" role="button">Generar RSS</a>
        </div>
    </body>
</html>
