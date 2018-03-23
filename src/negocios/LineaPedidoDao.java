package negocios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import beans.LineaPedido;


public class LineaPedidoDao {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	private String sql;
	private Query query;
	private LineaPedido lineaPedido;
	public LineaPedidoDao() {
		super();
		lineaPedido=null;
		emf=Persistence.createEntityManagerFactory("Zlibreria");
		em= emf.createEntityManager();
		tx= em.getTransaction();
	}
	
	public LineaPedido findById(String clave)
	{
		
		if (!tx.isActive()) {
			tx.begin();
		}
		try {
			lineaPedido=em.find(LineaPedido.class, clave);
			em.flush();
		} catch (PersistenceException e) {
			tx.rollback();
			System.out.println("find");
			e.printStackTrace();
		}
		
		return lineaPedido;
	}
	
	public int insertar(LineaPedido linea)
	{
		int filas=-1;
		if (!tx.isActive()) {
			tx.begin();
		}
		try {
			em.merge(linea);
			tx.commit();
			filas=1;
		} catch (PersistenceException e) {
			System.out.println("insert");
			filas=-1;
			e.printStackTrace();
		}	
		return filas;
	}
	public List<LineaPedido> lineasPedidos()//estas querys se utilizan los objetos en el select
	{
		if (!tx.isActive()) {
			tx.begin();
		}
		List<LineaPedido> lped=new ArrayList<LineaPedido>();
		
			query = em.createNamedQuery("LineaPedido.findAll", LineaPedido.class);
		    lped=query.getResultList(); 
		return lped;
	}
	public int eliminar(LineaPedido linea)
	{
		int filas=1;
		if (!tx.isActive()) {
			tx.begin();
		}
		if (!em.contains(linea)) {
			linea=em.merge(linea);
		}
		try {
			em.remove(linea);
			tx.commit();
			
		} catch (PersistenceException e) {
			System.out.println("remove");
			filas=-1;
			e.printStackTrace();
		}
		return filas;
	}

}
