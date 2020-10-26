<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
      <script type = "text/javascript" >  
    function preventBack() { window.history.forward(); }  
    setTimeout("preventBack()", 0);  
    window.onunload = function () { null };  
</script> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bicicleteria</title>
  </head>
  <body>
    <h1>Encargado</h1>
    <p>
      Hola ${nombre}!
    </p>

    <form method="post" action="ControladorStockPiezas">
      <select name="nuevaPieza">
        <c:forEach var="pieza" items="${recursos}">
            <option value="${pieza}">${pieza}</option>
        </c:forEach>
      </select>
      <p><label>código</label><br><input type="text" name="codigo"></p>
      <input type="submit" value="Alta">
    </form>

    <form method="post" action="ControladorLogout">
      <input type="submit" value="Salir" >
    </form>
  </body>
</html>
