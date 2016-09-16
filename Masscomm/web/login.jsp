<%-- 
    Document   : login
    Created on : 16-sep-2016, 10:55:47
    Author     : pmayor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de sesión</title>
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">
        <link rel="stylesheet" type="text/css" href="CSS/custom.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        <div id="login_container" class="container">
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="inputUser" class="col-sm-4 control-label">Usuario</label>
                    <div class="col-sm-5">
                        <input type="email" class="form-control" id="inputEmail3" placeholder="Nombre de usuario">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-sm-4 control-label">Contraseña</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" id="inputPassword3" placeholder="Contraseña">
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
