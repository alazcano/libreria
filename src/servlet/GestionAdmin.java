package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Libro;
import beans.Pedido;
import beans.Tema;
import beans.Usuario;
import negocios.LibroDao;
import negocios.PedidosDao;
import negocios.TemaDao;
import negocios.UsuarioDao;

/**
 * Servlet implementation class GestionAdmin
 */
@WebServlet("/GestionAdmin")
public class GestionAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionAdmin() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre;
		Libro libro;
		LibroDao ldao;
		Tema tema;
		TemaDao tdao;
		HttpSession misesion = request.getSession();
		response.setContentType("text/html");
		ldao= new LibroDao();
		tdao= new TemaDao();
		List<Pedido> listaP;
		PedidosDao pdao= new PedidosDao();
		switch (request.getParameter("opcion")) {
		case "tema":
			tema= new Tema( request.getParameter("abreviatura"), request.getParameter("nombre"));	
			if (tdao.insertar(tema)==1) {
				misesion.setAttribute("mensaje", "Tema registrado");
				misesion.setAttribute("privilegio", "admin");
				response.sendRedirect("ErrorUsuario.jsp");
			} else {
				misesion.setAttribute("mensaje", "Tema ya exisistente");
				misesion.setAttribute("privilegio", "admin");
				response.sendRedirect("ErrorUsuario.jsp");
			}
			
			
			break;
		case "libro":
			tema= tdao.findById(Long.parseLong(request.getParameter("idTema")));
			if (tema==null) {
				misesion.setAttribute("mensaje", "No existe ese tema");
				misesion.setAttribute("privilegio", "admin");
				response.sendRedirect("ErrorUsuario.jsp");
			}else{
				libro= new Libro(request.getParameter("isbn"), request.getParameter("autor"), BigDecimal.valueOf(Double.parseDouble(request.getParameter("precio"))), 
						BigDecimal.valueOf(Double.parseDouble(request.getParameter("stock"))), request.getParameter("titulo"), tema);
				
				if (ldao.insertar(libro)==1) {
					misesion.setAttribute("mensaje", "Libro registrado ");
					misesion.setAttribute("privilegio", "admin");
					response.sendRedirect("ErrorUsuario.jsp");
				} else {
					misesion.setAttribute("mensaje", "Libro ya exisistente");
					misesion.setAttribute("privilegio", "admin");
					response.sendRedirect("ErrorUsuario.jsp");
				}
			}
			break;
		case "pedidos":
			UsuarioDao udao= new UsuarioDao();
			nombre=(String)request.getParameter("nombre");
			Usuario usuario=udao.findById(nombre);
			listaP=pdao.pedidosUsuario(usuario);
			misesion.setAttribute("pedidos", listaP);
			response.sendRedirect("ListaPedidos.jsp");
			break;
		case "fecha":
			nombre=(String)request.getParameter("nombre");
			listaP=pdao.listaPedidos();
			misesion.setAttribute("pedidos", listaP);
			response.sendRedirect("ListaPedidos.jsp?fecha="+nombre);
			break;
		case "estadisticas":
			nombre=(String)request.getParameter("nombre");
			Object[] blist=pdao.nTemas(nombre, "carrito");
			misesion.setAttribute("estadisticasCesta", blist);
			blist=pdao.nTemas(nombre, "comprado");
			misesion.setAttribute("estadisticasCompra", blist);
			response.sendRedirect("Estadisticas.jsp?nombre="+nombre);
			break;
		case "cerrar":
			misesion.invalidate();
			response.sendRedirect("Index.html");
			break;

		default:
			break;
		}
	}

}
