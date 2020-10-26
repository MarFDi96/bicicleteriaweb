<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Bicicleteria</title>
    </head>
    <body>
        <h1>ERROR</h1>
        <p>
            ${mensajeError}
        </p>
        <form method="post" action="ControladorLogout">
            <input type="submit" value="Salir" >
        </form>
    </body>
</html>
