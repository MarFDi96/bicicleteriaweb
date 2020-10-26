<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bicicleteria</title>
  </head>
  <body>

        <c:forEach var = "pieza" items="${Piezas}" >
            
            <p>pieza  ${pieza.getPieza()}</p>
           <p>codigo ${pieza.getCodigo()}</p>
           
         </c:forEach>

    <form method="post" action="ControladorLogout">
      <input type="submit" value="Salir" >
    </form>
  </body>
</html>