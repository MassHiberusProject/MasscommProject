<%-- 
    Document   : editarCumpleanios
    Created on : 20-sep-2016, 12:48:30
    Author     : claencina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
            <form class="form-horizontal" name="login" id="flogin" action="EditarCumpleanios" method="post">
                <div class="form-group">
                    <label for="inputNombre">Nombre</label>
                    <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" value=${cumpleanios.nombre}>
                </div>
                <div class="form-group">
                    <label for="inputApellidos">Apellidos</label>
                    <input type="text" class="form-control" name="apellidos" id="apellidos" placeholder="Apellidos" value=${cumpleanios.apellidos}>
                </div>
                <div class="form-group">
                    <label for="inputEmpresa">Empresa</label>
                    <input type="text" class="form-control" name="empresa" id="empresa" placeholder="Empresa" value=${cumpleanios.empresa}>
                </div>
                <div class="form-group">
                    <label for="inputFecha">Fecha</label>
                    <input type="date" class="form-control" name="fecha" id="fecha" placeholder="Fecha"  value="<fmt:formatDate pattern="dd/MM/yyyy" value="${cumpleanios.fecha}" />"/>                   
                </div>


                <div class="form-group">
                    <label for="inputFoto">Foto</label>
                    <a href="${cumpleanios.foto}">enlace foto</a>
                    <input type="file" name="foto" id="foto">
                </div>
                <button type="submit" class="btn btn-primary">Modificar</button>
            </form>
        </div>
    </body>
</html>
