package negocios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;


import beans.Pedido;
import beans.Usuario;


public class PedidosDao {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	private String sql;
	private Query query;
	private Pedido pedido;
	public PedidosDao() {
		super();
		pedido=null;
		emf=Persistence.createEntityManagerFactory("Zlibreria");
		em= emf.createEntityManager();
		tx= em.getTransaction();
	}
	
	public Pedido findById(String clave)
	{
		
		if (!tx.isActive()) {
			tx.begin();
		}
		try {
			pedido=em.find(Pedido.class, clave);
		} catch (PersistenceException e) {
			tx.rollback();
			System.out.println("find");
			e.printStackTrace();
		}
		
		return pedido;
	}
	public int insertar(Pedido ped)
	{
		int filas=-1;
		if (!tx.isActive()) {
			tx.begin();
		}
		
		try {
			em.persist(ped);
			tx.commit();
			filas=1;
		} catch (PersistenceException e) {
			System.out.println("insert");
			filas=-1;
			e.printStackTrace();
		}	
		return filas;
	}
	
	public List<Pedido> listaPedidos()//estas querys se utilizan los objetos en el select
	{
		if (!tx.isActive()) {
			tx.begin();
		}
		List<Pedido> lped=new ArrayList<Pedido>();
		
			query = em.createNamedQuery("Pedido.findAll", Pedido.class);
		    lped=query.getResultList(); 
		return lped;
	}
	public List<Pedido> pedidosUsuario(Usuario usuario)
	{
		if (!tx.isActive()) {
			tx.begin();
		}
		List<Pedido> lped=new ArrayList<Pedido>();
	    sql= new String("SELECT p FROM Pedido p WHERE p.usuario = ?1 ORDER BY p.fechaAlta DESC");
		query = em.createQuery(sql, Pedido.class);
		query.setParameter(1, usuario);
		 lped=query.getResultList(); 
		return lped;
	}
	public int eliminar(Pedido ped)
	{
		int filas=1;
		if (!tx.isActive()) {
			tx.begin();
		}
		if (!em.contains(ped)) {
			ped=em.merge(ped);
		}
		try {
			em.remove(ped);
			tx.commit();
		} catch (PersistenceException e) {
			System.out.println("remove");
			filas=-1;
			e.printStackTrace();
		}
		return filas;
	}
	public boolean pedidoVacio(Pedido ped)
	{
		if (ped.getLineaPedidos()==null) {
			return true;
		} else {
			return false;
		}

	}
	public long nPedidoSig()
	{
		if (!tx.isActive()) {
			tx.begin();
		}
		sql= new String("SELECT MAX(p.idPedido) FROM Pedido p");
		
		query = em.createQuery(sql, Long.class);
		if (query==null) {
			return 0;
		}
		return (Long)query.getSingleResult();
	}
	
	public Object[] nTemas(String nombre, String estado)
	{
		Object[] obj;

		if (!tx.isActive()) {
			tx.begin();
		}
		sql= new String("SELECT COUNT(DISTINCT l.ID_TEMA), NVL(SUM(lp.PRECIO_VENTA),0), COUNT(lp.ISBN) "
				+ "FROM PEDIDOS p JOIN LINEA_PEDIDOS lp ON p.ID_PEDIDO = lp.ID_PEDIDO JOIN LIBROS l ON lp.ISBN LIKE l.ISBN "
				+ "WHERE p.ID_USUARIO LIKE ? AND p.ESTADO LIKE ?");
		
		query = em.createNativeQuery(sql);
		query.setParameter(1, nombre);
		query.setParameter(2, estado);
		obj=(Object[])query.getSingleResult();


		if (query==null) {
			return null;
		}
		return obj;
	}
	
}
