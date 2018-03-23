<%@page import="java.util.List"%>
<%@page import="beans.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="estilos.css" />
<title>Carrito</title>
</head>
<body class="reg">
<%Usuario usuarioActivo=(Usuario)session.getAttribute("usuario"); 
List<Pedido> todosPedidos=(List<Pedido>)session.getAttribute("pedidos");%>
	<div class="panelPadre">
		<div class="panelCesta"><center>
		<%if(usuarioActivo==null) {%>
			<h1>Carrito de Visitante</h1>
			<%}else{ %>
			<h1>Carrito de <%=usuarioActivo.getNombre()%></h1><%} %>
	
	<%if(todosPedidos!=null){%>
		
			<table id="cesta">
				<tr>
					<th>ID Pedido</th>
					<th>Titulo</th>
					<th>Autor</th>
					<th>Tema</th>
					<th>Precio</th>
					<th>Cantidad</th>
				
				</tr>
				<%for (Pedido elem: todosPedidos){
					if(elem.getUsuario().equals(usuarioActivo) && elem.getEstado().equals("carrito")){
						for(LineaPedido p: elem.getLineaPedidos()){%>
							<tr>

								<td><%= elem.getIdPedido()%></td>
								<td><%= p.getLibro().getTitulo()%></td>
								<td><%= p.getLibro().getAutor()%></td>
								<td><%= p.getLibro().getTema().getDescTema()%></td>
								<td><%= p.getPrecioVenta()%></td>
								<td><%= p.getCantidad()%></td>
								<td><button class="eliminar" type="button" name="eliminar"  onClick="window.location.href('GestionLibros?opcion=eliminar<%session.setAttribute("del", p);%>');">Eliminar</button>
								</td>
							</tr>
				<%}
					}
						} %>
			</table>
	<%}else%><h3>No tienes nada en tu carrito</h3><br>
	<br><button class="carrito" type="button" name="comprar"  onClick="window.location.href('GestionLibros?opcion=comprar');">Comprar</button>
	<button class="eliminar" type="button" name="vaciar"  onClick="window.location.href('GestionLibros?opcion=vaciar');">Vaciar carrito</button><br><br>
	<button class="normal" type="button" name="volver"  onClick="window.location.href('MenuCliente.jsp');">Volver al menu</button>
	<%if(usuarioActivo!=null) { %>
	<button class="normal" type="button" name="cerrar"  onClick="window.location.href('GestionLibros?opcion=cerrar');">Cerrar sesion</button>
	<%} %>
	
	<p></p>
	</div>
		</div></center>



</body>
</html>