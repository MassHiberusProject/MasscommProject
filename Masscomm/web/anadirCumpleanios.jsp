<%-- 
    Document   : anadirCumpleanios
    Created on : 26-sep-2016, 8:43:50
    Author     : claencina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/bootstrap.min.css" type="text/css"/>

        <script src="JS/bootstrap.min.js"></script>
        <title>JSP Page</title>  
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
        <script>
            $.datepicker.regional['es'] = {
                closeText: 'Cerrar',
                prevText: '<Ant',
                nextText: 'Sig>',
                currentText: 'Hoy',
                monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
                dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
                dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mié', 'Juv', 'Vie', 'Sáb'],
                dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sá'],
                weekHeader: 'Sm',
                dateFormat: 'dd/mm/yy',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: false,
                yearSuffix: ''
            };
            $.datepicker.setDefaults($.datepicker.regional['es']);
            $(function () {
                $("#fecha").datepicker();
            });
        </script>
    </head>
    <body>
        <%@include file="navbar.html" %>
        <div class="container">
            <c:if test="${contador!=0}">
                <div class="alert alert-danger">
                    <c:if test="${error_foto!=null}">
                        ${error_foto}
                    </c:if>
                </div>  
            </c:if>
            <form enctype="multipart/form-data" class="form-horizontal" name="login" id="flogin" action="AnadirCumpleanios" method="post">
                <div class="form-group">
                    <label for="inputNombre">Nombre *</label>
                    <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" value="${nombre}" required="true">
                </div>
                <div class="form-group">
                    <label for="inputApellidos">Apellidos</label>
                    <input type="text" class="form-control" name="apellidos" id="apellidos" placeholder="Apellidos" value="${apellidos}">
                </div>
                <div class="form-group">
                    <label for="inputEmpresa">Empresa</label>
                    <input type="text" class="form-control" name="empresa" id="empresa" placeholder="Empresa" value="${empresa}">
                </div>
                <div class="form-group">
                    <label for="inputFecha">Fecha *</label>
                    <input required="true" type="date" class="form-control" name="fecha" id="fecha" placeholder="<fmt:formatDate pattern="dd/MM/yyyy" value="${fecha!=null ? fecha : fech}" />" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${f}" />">                   
                </div>

                <div class="form-group">
                    <label for="inputFoto">Foto</label>
                    <input type="file" name="foto" id="foto">
                </div>
                <button type="submit" class="btn btn-primary">Guargar</button>
                <a class="btn btn-primary" href="Cumpleanios" role="button">Cancelar</a>
            </form>
        </div>
    </body>
</html>

