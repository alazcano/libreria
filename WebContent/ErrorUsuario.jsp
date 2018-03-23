<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<link rel="stylesheet" type="text/css" href="estilos.css" />
</head>
<body class="reg">
	<div class="panelPadre">
		<div class="panelError"><center>
			<h1><%=session.getAttribute("mensaje")%></h1>
			<%if(session.getAttribute("privilegio")==null){ %><br>
			<button class="normal" type="button" name="inicio"  onClick="window.location.href('Index.html')">Volver a inicio</button><br><br>

			<%}else if("admin".equals(session.getAttribute("privilegio"))){ %><br>
			<button class="normal" type="button" name="temas"  onClick="window.location.href('Admin.jsp')">Volver al menu de administrador</button><br><br>

			<%} %>
		</center></div>
	</div>
</body>
</html>