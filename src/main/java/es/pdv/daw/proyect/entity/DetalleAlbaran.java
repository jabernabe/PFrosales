package es.pdv.daw.proyect.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="detalle_albaran")
public class DetalleAlbaran implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * identificador detalle albaran.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idDetalleAlbaran;
	
	/**
	 * Albaran al que se referencia.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="albaran")
	private Albaran albaran;
	
	/**
	 * producto rosal al que se referencia.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="rosal")
	private Rosal rosal;
	
	/**
	 * Cantidad de rosales.
	 */
	@Column(name ="cantidad", length=6, nullable=false)
	private int cantidad;

	public DetalleAlbaran() {
		super();
		
	}

	// Metodos setter y getter.
	
	public int getIdDetalleAlbaran() {
		return idDetalleAlbaran;
	}

	public void setIdDetalleAlbaran(int idDetalleAlbaran) {
		this.idDetalleAlbaran = idDetalleAlbaran;
	}

	public Albaran getAlbaran() {
		return albaran;
	}

	public void setAlbaran(Albaran albaran) {
		this.albaran = albaran;
	}

	public Rosal getRosal() {
		return rosal;
	}

	public void setRosal(Rosal rosal) {
		this.rosal = rosal;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
