<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bicicleteria</title>
  </head>
  <body>
    <h1>Bicicleteria</h1>
    <form method="post" action="ControladorLogin">
      <p><label>Usuario:</label><br><input type="text" name="usuario" ></p>
      <p><label>Contraseña: </label><br><input type="password" name="password" ></p>
      <p><input type="submit" value="Entrar"></p>
    </form>
  </body>
</html>
