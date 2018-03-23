<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dar de alta</title>
</head>
<body><%if("libro".equals(request.getParameter("opcion"))){ %>
	<form action="GestionAdmin?opcion=libro" method="post">
		<h1>Alta</h1>
		<input type="text" name="isbn" placeholder="ISBN del libro"
			value=""><br><br>
		<input type="text" name="titulo" placeholder="Titulo del libro" value=""
			minlength="4" maxlength="8"><br>
		<br> <input type="text" name="autor" placeholder="Autor del libro"
			value=""><br>
		<br> <input type="text" name="precio" placeholder="Precio del libro"
			value=""><br>
		<br> <input type="text" name="stock" placeholder="Stock del libro"
			value=""><br>
		<br> <input type="text" name="idTema" placeholder="Id del tema del libro" value=""><br><br> 
		<button class="submit" type="submit">Enviar</button><br>
		<br>
	</form>
	<%}else if("tema".equals(request.getParameter("opcion"))){ %>
	<form action="GestionAdmin?opcion=tema" method="post">
		<h1>Registro</h1>
		<input type="text" name="idTema" placeholder="ID Tema" value=""><br>
		<br> <input type="text" name="nombre" placeholder="Nombre del tema"
			value=""><br>
		<br> <input type="text" name="abreviatura" placeholder="Abreviatura"
			value=""><br>
		<button class="submit" type="submit">Enviar</button><br>
		<br>

	</form>
	<%} %>
	<a href="Admin.jsp">Atras</a>

</body>
</html>