<%-- 
    Document   : editarCumpleanios
    Created on : 20-sep-2016, 12:48:30
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
            <form enctype="multipart/form-data" class="form-horizontal" name="login" id="flogin" action="EditarCumpleanios" method="post">
                <div class="form-group">
                    <label for="inputNombre">Nombre *</label>
                    <input required="true" type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" value="${cumpleanios.nombre!=null ? cumpleanios.nombre : nombre}">
                </div>
                <div class="form-group">
                    <label for="inputApellidos">Apellidos</label>
                    <input type="text" class="form-control" name="apellidos" id="apellidos" placeholder="Apellidos" value="${cumpleanios.apellidos!=null ? cumpleanios.apellidos : apellidos}">
                </div>
                <div class="form-group">
                    <label for="inputEmpresa">Empresa</label>
                    <input type="text" class="form-control" name="empresa" id="empresa" placeholder="Empresa" value="${cumpleanios.empresa!=null ? cumpleanios.empresa : empresa}">
                </div>
                <div class="form-group">
                    <label for="inputFecha">Fecha *</label>
                    <input required="true" type="date" class="form-control" name="fecha" id="fecha" placeholder="<fmt:formatDate pattern="dd/MM/yyyy" value="${fecha!=null ? fecha : fech}" />"  value="<fmt:formatDate pattern="dd/MM/yyyy" value="${cumpleanios.fecha!=null ? cumpleanios.fecha : fech}" />"/>                
                </div>

                <div class="form-group">
                    <label for="inputFoto">Foto</label>
                    <c:if test="${cumpleanios.imagen!=null}">
                        <a target="_blank" href="img/${cumpleanios.imagen}">Foto</a>
                    </c:if> 
                    <c:if test="${foto_id!=null}">
                        <a target="_blank" href="img/${foto_id}">Foto</a>
                    </c:if> 
                    <input type="file" name="foto" id="foto">
                </div>
                <button type="submit" class="btn btn-primary">Modificar</button>
                <a class="btn btn-primary" href="Cumpleanios" role="button">Cancelar</a>
            </form>
        </div>
    </body>
</html>
