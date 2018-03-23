package beans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import static javax.persistence.CascadeType.ALL;


/**
 * The persistent class for the PEDIDOS database table.
 * 
 */
@Entity
@Table(name="PEDIDOS")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PEDIDO")
	private long idPedido;

	@Column(name="DIRECCION_ENTREGA")
	private String direccionEntrega;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	//bi-directional many-to-one association to LineaPedido
	@OneToMany(mappedBy="pedido", cascade = ALL)
	private List<LineaPedido> lineaPedidos;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

	public Pedido() {
	}
	public Pedido(long idPedido) {
		super();
		this.idPedido = idPedido;
		this.direccionEntrega = null;
		this.estado = null;
		this.fechaAlta = null;
		this.lineaPedidos = null;
		this.usuario = null;
	}
	public Pedido(long idPedido, String direccionEntrega, String estado, Date fechaAlta, Usuario usuario) {
		super();
		this.idPedido = idPedido;
		this.direccionEntrega = direccionEntrega;
		this.estado = estado;
		this.fechaAlta = fechaAlta;

		this.usuario = usuario;
	}

	public long getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public String getDireccionEntrega() {
		return this.direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public List<LineaPedido> getLineaPedidos() {
		return this.lineaPedidos;
	}

	public void setLineaPedidos(List<LineaPedido> lineaPedidos) {
		this.lineaPedidos = lineaPedidos;
	}

	public LineaPedido addLineaPedido(LineaPedido lineaPedido) {
		getLineaPedidos().add(lineaPedido);
		lineaPedido.setPedido(this);

		return lineaPedido;
	}

	public LineaPedido removeLineaPedido(LineaPedido lineaPedido) {
		getLineaPedidos().remove(lineaPedido);
		lineaPedido.setPedido(null);

		return lineaPedido;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idPedido ^ (idPedido >>> 32));
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
		Pedido other = (Pedido) obj;
		if (idPedido != other.idPedido)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", direccionEntrega=" + direccionEntrega + ", estado=" + estado
				+ ", fechaAlta=" + fechaAlta + ", usuario=" + usuario + "]";
	}

}