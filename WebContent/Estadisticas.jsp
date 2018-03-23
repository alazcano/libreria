<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Estadisticas del usuari@ <%=request.getParameter("nombre") %></title>
<%Object[] comprados= (Object[]) session.getAttribute("estadisticasCompra"); 
Object[] cesta= (Object[]) session.getAttribute("estadisticasCesta"); %>
<link rel="stylesheet" type="text/css" href="estilos.css" />
</head>
<body class="reg"><center>
<div class="panelPadre"><div class="panelEstadisticas">
	<h1>Estadisticas de <%=request.getParameter("nombre") %></h1><br>
	<table id="cesta">
				<tr>
					<th>Estado</th>
					<th>Cuantos temas tienen los pedidos</th>
					<th>Numero de libros</th>
					<th>Precio total</th>
				</tr>
				<tr>
					<th>En carrito</th>
					<th><%=cesta[0] %></th>
					<th><%=cesta[2] %></th>
					<th><%=cesta[1] %></th>
				</tr>
				<tr>
					<th>Comprados</th>
					<th><%=comprados[0] %></th>
					<th><%=comprados[2] %></th>
					<th><%=comprados[1] %></th>
					</tr>
			</table>
			<br>
			<button class="normal" type="button" name="volver"  onClick="window.location.href('Admin.jsp');">Volver al menu</button>
		<p></p>
		</div></div></center>
</body>
</html>