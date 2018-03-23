package negocios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import beans.Libro;



public class LibroDao {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	private String sql;
	private Libro libro;
	public LibroDao() {
		super();
		libro=null;
		emf=Persistence.createEntityManagerFactory("Zlibreria");
		em= emf.createEntityManager();
		tx= em.getTransaction();
	}
	
	public Libro findById(String clave)
	{
		
		if (!tx.isActive()) {
			tx.begin();
		}
		try {
			libro=em.find(Libro.class, clave);
		} catch (PersistenceException e) {
			tx.rollback();
			System.out.println("find");
			e.printStackTrace();
		}
		
		return libro;
	}
	
	public int insertar(Libro libro)
	{
		int filas=-1;
		if (!tx.isActive()) {
			tx.begin();
		}
		try {
			em.merge(libro);
			tx.commit();
			filas=1;
		} catch (PersistenceException e) {
			System.out.println("insert");
			filas=-1;
			e.printStackTrace();
		}	
		return filas;
	}

	
}
