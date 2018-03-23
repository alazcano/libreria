<%@page import="java.text.SimpleDateFormat"%>
<%@page import="beans.LineaPedido"%>
<%@page import="beans.Pedido"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pedidos</title>
<link rel="stylesheet" type="text/css" href="estilos.css" />

</head>
<body class="reg">
	<%List<Pedido> todosPedidos=(List<Pedido>)session.getAttribute("pedidos");
	SimpleDateFormat dt = new SimpleDateFormat ("yyyy-MM-dd");
	String fecha=(String)request.getParameter("fecha"), fechaB;
%>
	<center><div class="panelPadre"><div class="panelCesta">
	<h1>Pedidos</h1><br>
	<%if(todosPedidos!=null){%>
	
		<table id="cesta">
			<tr>
				<th>Fecha</th>
				<th>ID Pedido</th>
				<th>Usuario</th>
				<th>Isbn</th>
				<th>Precio</th>
				<th>Cantidad</th>
				<th>Estado</th>

			</tr>
			<%for (Pedido elem: todosPedidos){
				fechaB=dt.format(elem.getFechaAlta());
						for(LineaPedido p: elem.getLineaPedidos()){
			if(fecha!=null){
				if(fechaB.equals(fecha)){ %>
					<tr>
						<td><%= dt.format(elem.getFechaAlta())%></td>
						<td><%= elem.getIdPedido()%></td>
						<td><%=elem.getUsuario().getNombre() %></td>
						<td><%= p.getLibro().getTitulo()%></td>
						<td><%= p.getPrecioVenta()%></td>
						<td><%= p.getCantidad()%></td>
						<td><%=elem.getEstado() %></td>
						<td><button class="eliminar" type="button" name="eliminar"  onClick="window.location.href('GestionLibros?opcion=eliminar<%session.setAttribute("del", p);%>');">Eliminar</button><br></td>
					</tr>
			<%}
				}else{%>
					<tr>
					<td><%= dt.format(elem.getFechaAlta())%></td>
					<td><%= elem.getIdPedido()%></td>
					<td><%=elem.getUsuario().getNombre() %></td>
					<td><%= p.getLibro().getTitulo()%></td>
					<td><%= p.getPrecioVenta()%></td>
					<td><%= p.getCantidad()%></td>
					<td><%=elem.getEstado() %></td>
					<td><button class="eliminar" type="button" name="eliminar"  onClick="window.location.href('GestionLibros?opcion=eliminar<%session.setAttribute("del", p);%>');">Eliminar</button><br></td>
				</tr>
			<%}
			}
					
						} %>
		</table>
		<%}else%><h3>No tienes nada en tu carrito</h3>
		<br>
	<button class="normal" type="button" name="volver"  onClick="window.location.href('Admin.jsp');">Volver al menu</button>
	<button class="normal" type="button" name="cerrar"  onClick="window.location.href('GestionLibros?opcion=cerrar');">Cerrar sesion</button>
	<p></p>
	</div></div></center>
	
</body>
</html>