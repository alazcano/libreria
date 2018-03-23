package beans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TEMAS database table.
 * 
 */
@Entity
@Table(name="TEMAS")
@NamedQuery(name="Tema.findAll", query="SELECT t FROM Tema t")
public class Tema implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="TEMAS_TEMAID_GENERATOR", sequenceName="TEMAS_SEC", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEMAS_TEMAID_GENERATOR")
	@Column(name="ID_TEMA")
	private long idTema;

	private String abreviatura;

	@Column(name="DESC_TEMA")
	private String descTema;

	//bi-directional many-to-one association to Libro
	@OneToMany(mappedBy="tema")
	private List<Libro> libros;

	public Tema() {
	}

	public Tema( String abreviatura, String descTema) {
		super();
		this.abreviatura = abreviatura;
		this.descTema = descTema;
	}
	public Tema(long idTema, String abreviatura, String descTema) {
		super();
		this.idTema = idTema;
		this.abreviatura = abreviatura;
		this.descTema = descTema;
	}

	public long getIdTema() {
		return this.idTema;
	}

	public void setIdTema(long idTema) {
		this.idTema = idTema;
	}

	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getDescTema() {
		return this.descTema;
	}

	public void setDescTema(String descTema) {
		this.descTema = descTema;
	}

	public List<Libro> getLibros() {
		return this.libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public Libro addLibro(Libro libro) {
		getLibros().add(libro);
		libro.setTema(this);

		return libro;
	}

	public Libro removeLibro(Libro libro) {
		getLibros().remove(libro);
		libro.setTema(null);

		return libro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idTema ^ (idTema >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tema other = (Tema) obj;
		if (idTema != other.idTema)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tema [idTema=" + idTema + ", abreviatura=" + abreviatura + ", descTema=" + descTema + "]";
	}

}