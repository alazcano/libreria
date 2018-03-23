<%@page import="beans.Libro"%>
<%@page import="beans.Tema"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Catalogo de temas</title>
<link rel="stylesheet" type="text/css" href="estilos.css" />
</head>
<body class="reg">
<center>
	<div class="panelPadre">
		<div class="panelCesta">
			<%List<Tema> listaT= (List<Tema>)session.getAttribute("temas"); %>
			<h1>Temas</h1><br>
			<%for(Tema elem: listaT){ %>
				<button class="normal" type="button" name="tema"  onClick="window.location.href('GestionLibros?opcion=temas&identificador=<%=elem.getIdTema()%>');"><%=elem.getDescTema() %></button>
			
			<%} %>
				<br><br>
			<%List<Libro> listaL =(List<Libro>)session.getAttribute("Libros");  
			
				if(listaL!=null){%>
				<form action="GestionLibros?opcion=pedido<%session.setAttribute("Libros", null); %>" method="post" >
				  
					<%for(Libro elem: listaL){ %>
					<label class="libros"><%=elem.getTitulo()%>, <%=elem.getAutor() %>
						  <input type="checkbox" name="libro" value="<%= elem.getIsbn()%>">
						  <span class="checkmark"></span>
					</label>
					
					<%} %>
					<br><button class="submit" type="submit">Enviar</button>
				</form>
				<%} %>
				<br>
				<button class="normal" type="button" name="volver"  onClick="window.location.href('MenuCliente.jsp');">Volver al menu</button>

		<p></p>
		</div>
	</div>
</center>
</body>
</html>