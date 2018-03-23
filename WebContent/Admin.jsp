<%@page import="java.text.SimpleDateFormat"%>
<%@page import="beans.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrador</title>
<%Usuario usuarioActivo=(Usuario)session.getAttribute("usuario"); %>
<link rel="stylesheet" type="text/css" href="estilos.css" />
</head>
<body class="reg"><center>
	<div class="panelPadre"><div class="panelEstadisticas">
		<h1>Vista del administrador	<%=usuarioActivo.getNombre()%></h1><br>

		<button class="normal" type="button" name="altat"  onClick="window.location.href('Alta.jsp?opcion=tema');">Alta Tema</button>
		<button class="normal" type="button" name="altal"  onClick="window.location.href('Alta.jsp?opcion=libro');">Alta Libro</button>
		<br><br>
		<form class="formulario" action="GestionAdmin?opcion=pedidos" method="post">
			<label class="label">Buscar pedidos por usuario</label><br>
			<input type="text" name="nombre" placeholder="usuario" value=""> 
			<button class="normal" type="submit">Enviar</button>
			
		</form><br>
		<form class="formulario" action="GestionAdmin?opcion=fecha" method="post">
			<label class="label">Buscar pedidos por fechas</label><br>
			<input type="text" name="nombre" placeholder="aaaa-mm-dd" value=""> 
			<button class="normal" type="submit">Enviar</button>
			<br>
		</form>
		<br>
		<form class="formulario" action="GestionAdmin?opcion=estadisticas" method="post">
			<label class="label">Estadisticas de un usuario</label><br>
			<input type="text" name="nombre" placeholder="usuario" value=""> 
			<button class="normal" type="submit">Enviar</button>
			<br>
		</form>
		<br>
		<button class="normal" type="button" name="cerrar"  onClick="window.location.href('GestionAdmin?opcion=cerrar');">Cerrar sesion</button>

		<p></p>
	</div>	
	</div></center>
</body>
</html>