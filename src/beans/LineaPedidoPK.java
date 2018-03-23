package beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the LINEA_PEDIDOS database table.
 * 
 */
@Embeddable
public class LineaPedidoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String isbn;

	@Column(name="ID_PEDIDO", insertable=false, updatable=false)
	private long idPedido;

	public LineaPedidoPK() {
	}
	
	public LineaPedidoPK(String isbn, long idPedido) {
		super();
		this.isbn = isbn;
		this.idPedido = idPedido;
	}

	public String getIsbn() {
		return this.isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public long getIdPedido() {
		return this.idPedido;
	}
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LineaPedidoPK)) {
			return false;
		}
		LineaPedidoPK castOther = (LineaPedidoPK)other;
		return 
			this.isbn.equals(castOther.isbn)
			&& (this.idPedido == castOther.idPedido);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.isbn.hashCode();
		hash = hash * prime + ((int) (this.idPedido ^ (this.idPedido >>> 32)));
		
		return hash;
	}
}