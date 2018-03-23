package negocios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import beans.Tema;


public class TemaDao {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	private Query query;
	private String sql;
	private Tema tema;
	public TemaDao() {
		super();
		tema=null;
		emf=Persistence.createEntityManagerFactory("Zlibreria");
		em= emf.createEntityManager();
		tx= em.getTransaction();
	}
	
	public Tema findById(long clave)
	{
		
		if (!tx.isActive()) {
			tx.begin();
		}
		try {
			tema=em.find(Tema.class, clave);
		} catch (PersistenceException e) {
			tx.rollback();
			System.out.println("find");
			e.printStackTrace();
		}
		
		return tema;
	}
	public int insertar(Tema tema)
	{
		int filas=-1;
		if (!tx.isActive()) {
			tx.begin();
		}
		try {
			em.persist(tema);
			tx.commit();
			filas=1;
		} catch (PersistenceException e) {
			System.out.println("insert");
			filas=-1;
			e.printStackTrace();
		}	
		return filas;
	}
	public List<Tema> listaTemas()//estas querys se utilizan los objetos en el select
	{
		if (!tx.isActive()) {
			tx.begin();
		}
		List<Tema> temas=null;
		
			query = em.createNamedQuery("Tema.findAll", Tema.class);
		    temas=query.getResultList();
		    /*sql= new String("SELECT t FROM Tema t");
			query = em.createQuery(sql, Tema.class);*/

	     
		return temas;
	}
	
}
