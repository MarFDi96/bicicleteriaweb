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
    <h1>vendedor</h1>
    <p>
      Hola ${nombre}!
    </p>

    <form method="post" action="ControladorVendedor">
      <select name="bajaBicicleta">
        <c:forEach var="codigo" items="${recursos}">
            <option value="${codigo}">${codigo}</option>
        </c:forEach>
      </select>
      <input type="submit" value="Alta">
    </form>

    <form method="post" action="ControladorLogout">
      <input type="submit" value="Salir" >
    </form>
  </body>
</html>
