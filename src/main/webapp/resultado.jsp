<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bicicleteria</title>
  </head>
  <body>
    <h1>Éxito</h1>
    <p>
      ${mensaje}
    </p>

    <form method="post" action="ControladorLogout">
      <input type="submit" value="Salir" >
    </form>
  </body>
</html>
