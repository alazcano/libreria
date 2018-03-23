package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LineaPedido;
import beans.Pedido;
import beans.Usuario;
import negocios.LineaPedidoDao;
import negocios.PedidosDao;
import negocios.TemaDao;
import negocios.UsuarioDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		Usuario usuario;
		UsuarioDao udao;
		List<Pedido> listaP;
		PedidosDao pdao=new PedidosDao();
		TemaDao tdao;
		response.setContentType("text/html");
		udao= new UsuarioDao();
		tdao=new TemaDao();
		HttpSession misesion = request.getSession();
		listaP=pdao.listaPedidos();
		misesion.setAttribute("pedidos", listaP);
		switch (request.getParameter("opcion")) {
		case "validar":
			usuario= udao.findById(request.getParameter("usuario"));
			if ( usuario!= null && usuario.getPassword().equals(request.getParameter("password"))) {
				misesion.setAttribute("usuario", usuario);
				misesion.setAttribute("temas", tdao.listaTemas());
				listaP=pdao.pedidosUsuario(usuario);
				misesion.setAttribute("pedidos", listaP);
				if (usuario.getTipoUsuario().equals("admin")) {
					rd=request.getRequestDispatcher("Admin.jsp");
					rd.forward(request, response);
				}else {
					misesion.setAttribute("Libros", null);
					rd=request.getRequestDispatcher("MenuCliente.jsp");
					rd.forward(request, response);
				}
				
			}else {
				misesion.setAttribute("mensaje", "Usuario o contraseña erronea");
				misesion.setAttribute("usuario", null);
				rd=request.getRequestDispatcher("ErrorUsuario.jsp");
				rd.forward(request, response);
			}
			break;
		case "guest":
			misesion.setAttribute("temas", tdao.listaTemas());
			misesion.setAttribute("usuario", null);
			misesion.setAttribute("Libros", null);
			rd=request.getRequestDispatcher("MenuCliente.jsp");
			rd.forward(request, response);
			
			break;
			case "registrar":
				String id=request.getParameter("usuario"),pw=request.getParameter("password");
				if (!id.equals("") || !pw.equals("")) {
					usuario= new Usuario(id, request.getParameter("apellido"), 
							request.getParameter("direccion"), new Date(), request.getParameter("nombre"), 
							pw, "normal");
					if (usuario.getIdUsuario()!=null && usuario.getPassword()!=null && udao.insertar(usuario)==1) {
						misesion.setAttribute("mensaje", "Usuario registrado ");
						misesion.setAttribute("privilegio", null);
						
					} else {
						misesion.setAttribute("mensaje", "El usuario ya existe");
						misesion.setAttribute("privilegio", null);
					
					}
				} else {
					misesion.setAttribute("mensaje", "campos incorrectos");
					misesion.setAttribute("privilegio", null);
					
				}
				response.sendRedirect("ErrorUsuario.jsp");
				
				break;
			case "perfil":
				usuario=(Usuario)misesion.getAttribute("usuario");
				usuario.setPassword(request.getParameter("password"));
				usuario.setNombre(request.getParameter("nombre"));
				usuario.setApellido(request.getParameter("apellido"));
				usuario.setDireccion(request.getParameter("direccion"));
				if (udao.insertar(usuario)==-1) {
					System.out.println("error al modificar");
				}
				response.sendRedirect("MenuCliente.jsp");
				break;
		default:
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
