<%@page import="beans.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cliente</title>
<link rel="stylesheet" type="text/css" href="estilos.css" />
<%Usuario usuarioActivo=(Usuario)session.getAttribute("usuario"); 
%>
</head>
<body class="reg">
	<div class="panelPadre">
		<div class="panelMenuUsuario"><center>
			<%if(usuarioActivo==null) {%>
			<h1>Visitante</h1>
			<%}else{ %>
			<h1>Bienvenid@ <%=usuarioActivo.getNombre() %></h1><%} %>
			<br>
			<button class="normal" type="button" name="temas"  onClick="window.location.href('Temas.jsp')">Ver temas</button><br><br>
			<button class="normal" type="button" name="carrito"  onClick="window.location.href('GestionLibros?opcion=cesta')">Ver Carrito</button><br><br>
			<%if(usuarioActivo!=null) { %>
			<button class="normal" type="button" name="cliente"  onClick="window.location.href('DatosUsuario.jsp?opcion=0')">Datos Cliente</button><br><br>
			<button class="normal" type="button" name="cerrar"  onClick="window.location.href('GestionLibros?opcion=cerrar')">Cerrar sesion</button><br><br>
			<%}else{ %>
			<button class="normal" type="button" name="login"  onClick="window.location.href('Index.html')">Inicar sesion o registrarse</button>
			<%} %>
			<p></p>
			</center>
		</div>
	</div>
</body>
</html>