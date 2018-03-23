package negocios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


import beans.Usuario;

public class UsuarioDao {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	private String sql;
	private Usuario usuario;
	
	public UsuarioDao() {
		super();
		usuario=null;
		emf=Persistence.createEntityManagerFactory("Zlibreria");
		em= emf.createEntityManager();
		tx= em.getTransaction();
	}
	
	public Usuario findById(String clave)
	{
		
		tx.begin();
		try {
			usuario=em.find(Usuario.class, clave);
		} catch (PersistenceException e) {
			tx.rollback();
			System.out.println("find");
			e.printStackTrace();
		}
		
		return usuario;
	}
	public int insertar(Usuario cliente)
	{
		int filas=-1;
		tx.begin();
		try {
			em.persist(cliente);
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
