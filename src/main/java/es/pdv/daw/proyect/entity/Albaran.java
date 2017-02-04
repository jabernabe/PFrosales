package es.pdv.daw.proyect.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que representa un albaran.
 * @author alumno
 *
 */
@Entity
@Table(name="Albaranes")
public class Albaran implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * identificador de albaran.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAlbaran;
	
	/**
	 * Fecha de emision del albaran.
	 */
	@Column(name ="fecha", nullable=false)
	private Date fecha;
	
	/**
	 * Cliente al que referencia el albaran.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="cliente")
	private Cliente cliente;

	
	public Albaran() {
		super();
	}


	// Metodos setter y getter.
	
	public int getIdAlbaran() {
		return idAlbaran;
	}


	public void setIdAlbaran(int idAlbaran) {
		this.idAlbaran = idAlbaran;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
		

}
