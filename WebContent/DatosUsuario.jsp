<%@page import="beans.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perfil Usuario</title>
<%Usuario usuarioActivo=(Usuario)session.getAttribute("usuario");
int opc= Integer.parseInt(request.getParameter("opcion"));%>
<link rel="stylesheet" type="text/css" href="estilos.css" />
</head>
<body class="reg">
<center><div class="panelPadre"><div class="panelUsuario">
	<h1><%=usuarioActivo.getNombre() %> <%=usuarioActivo.getApellido() %></h1><br>
	
	<form action="Login?opcion=perfil" method="post">
		<label>Usuario : <%= usuarioActivo.getIdUsuario() %></label>
		<%if(opc==1) {%>
		<br><label style="color:red">*No se puede cambiar el identificador de usuario</label><%} %>
		<br><label>Contraseña : <%= usuarioActivo.getPassword()%></label>
		<%if(opc==1) {%>
		<br><input type="text" name="password" placeholder="Nueva contraseña" value=""
			minlength="4" maxlength="8"><%} %>
		<br><label>Fecha de alta : <%= usuarioActivo.getFechaAlta()%></label>
		<%if(opc==1) {%>
		<br><label style="color:red">*No se puede cambiar la fecha de alta</label><%} %>
		<br><label>Tipo de usuario : <%= usuarioActivo.getTipoUsuario()%></label>
		<%if(opc==1) {%>
		<br><label style="color:red">*No se puede cambiar el tipo de usuario</label><%} %>
		<br><label>Nombre de usuario : <%= usuarioActivo.getNombre() %></label>
		<%if(opc==1) {%>
		 <br><input type="text" name="nombre" placeholder="Nombre"	value=""><%} %>
		<br><label>Apellido de usuario : <%= usuarioActivo.getApellido()%></label>
		<%if(opc==1) {%>
		 <br><input type="text" name="apellido" placeholder="Apellido"	value=""><%} %>
		<br><label>Direccion de usuario: <%= usuarioActivo.getDireccion()%></label>
		<%if(opc==1) {%>
	 	<br><input type="text" name="direccion" placeholder="Direccion" value="">
	 	
	 	<br><button class="normal" type="submit">Enviar</button>
	 	<button class="normal" type="button" name="cancelar"  onClick="window.location.href('DatosUsuario.jsp?opcion=0');">Cancelar</button>
	</form>
	 <%} %>
	 <br>
	<button class="normal" type="button" name="volver"  onClick="window.location.href('MenuCliente.jsp');">Volver al menu</button>
	<button class="normal" type="button" name="editar"  onClick="window.location.href('DatosUsuario.jsp?opcion=1');">Editar perfil</button>

	<p></p>
	</div></div></center>
	
</body>
</html>