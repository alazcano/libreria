package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Libro;
import beans.LineaPedido;
import beans.LineaPedidoPK;
import beans.Pedido;
import beans.Tema;
import beans.Usuario;
import negocios.LibroDao;
import negocios.LineaPedidoDao;
import negocios.PedidosDao;
import negocios.TemaDao;

/**
 * Servlet implementation class GestionLibros
 */
@WebServlet("/GestionLibros")
public class GestionLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionLibros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long numeroTema;
		Usuario usuario;
		HttpSession misesion = request.getSession();
		response.setContentType("text/html");
		String opcion=(String) request.getParameter("opcion");//para el check box en con request.getParameterValues(arg0) que es string 
		List<Libro> listaL;
		List<Pedido> listaP = (List<Pedido>)misesion.getAttribute("pedidos");
		TemaDao tdao=new TemaDao();
		PedidosDao pdao= new PedidosDao();
		Pedido pedido;
		Libro libro;
		LibroDao ldao= new LibroDao();
		LineaPedido lineaPed;
		LineaPedidoDao lpdao= new LineaPedidoDao();
		usuario=(Usuario)misesion.getAttribute("usuario");
		
		switch (opcion) {
		case "pedido":
			String[] isb=request.getParameterValues("libro");
			if (isb!=null) {
				pedido= new Pedido();
				
				Long ident=pdao.nPedidoSig()+1;
				pedido= new Pedido(ident, usuario.getDireccion(), "carrito", new Date(), usuario);
				
				for (String elem : isb) {
		
					libro=ldao.findById(elem);
					lineaPed = new LineaPedido(BigDecimal.valueOf(1), new Date(), libro.getPrecioUnitario(), libro, pedido);

					lpdao.insertar(lineaPed);
					pedido.addLineaPedido(lineaPed);
				}
				listaP.add(pedido);
				misesion.setAttribute("pedidos", listaP);
				
				response.sendRedirect("GestionLibros?opcion=cesta");
			} else {
				response.sendRedirect("Temas.jsp");
			}
			
			break;
			
		case "cesta":
			//listaP= new ArrayList<Pedido>();

			//listaP=pdao.listaPedidos();
			//misesion.setAttribute("pedidos", listaP);
			response.sendRedirect("Carrito.jsp");
			
			break;
		case "vaciar":
			//listaP=pdao.listaPedidos();
			for (Pedido elem : listaP) {
				if ((elem.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) && (elem.getEstado().equals("carrito"))) {
					/*
					for(LineaPedido p: elem.getLineaPedidos())
					{
						lpdao.eliminar(p);
					}*/
					pdao.eliminar(elem);
					listaP.remove(elem);
				}
			}
			misesion.setAttribute("pedidos", listaP);
			response.sendRedirect("GestionLibros?opcion=cesta");
			break;
		case "eliminar":
			lineaPed=(LineaPedido)misesion.getAttribute("del");
			pedido=listaP.get(listaP.indexOf(lineaPed.getPedido()));
			lpdao.eliminar(lineaPed);
			pedido.removeLineaPedido(lineaPed);
			
			if (pedido.getLineaPedidos().size()==0) {
				pdao.eliminar(pedido);
				listaP.remove(pedido);
			}
			
			misesion.setAttribute("pedidos", listaP);
			response.sendRedirect("GestionLibros?opcion=cesta");
			break;
		case "comprar":
			listaP=pdao.listaPedidos();
			for (Pedido elem : listaP) {
				if ((elem.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) && (elem.getEstado().equals("carrito"))) {
					elem.setEstado("comprado");
					listaP.get(listaP.indexOf(elem)).setEstado("comprado");;
					pdao.insertar(elem);
					for (LineaPedido lp : elem.getLineaPedidos()) {
						libro=lp.getLibro();

						libro.setStock(libro.getStock().subtract(lp.getCantidad()));
						ldao.insertar(libro);
					}
					
				}
			}
			misesion.setAttribute("pedidos", listaP);
			response.sendRedirect("GestionLibros?opcion=cesta");
			break;
		case "cerrar":
			misesion.invalidate();
			response.sendRedirect("Index.html");
			break;
		case "temas":

			numeroTema=Long.parseLong((String) request.getParameter("identificador"));
			listaL=tdao.findById(numeroTema).getLibros();
			misesion.setAttribute("Libros", listaL);
			response.sendRedirect("Temas.jsp");
			break;
		default:
			break;
		}
		
	}
	

}
