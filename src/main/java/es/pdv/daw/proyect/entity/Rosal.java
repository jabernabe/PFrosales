package es.pdv.daw.proyect.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Entidad que representa la tabla rosales de la BD.
 * @author jabd.
 *
 */
@Entity
@Table(name="rosales")
public class Rosal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * identificador de rosal.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idRosal;
	
	/**
	 * propiedad que encapsula el nombre del rosal.
	 */
	@Column(name ="nombre_rosal", length=50, nullable=false, unique=true )
	private String nombreRosal;
	
	/**
	 * Propiedad que encapsula en color del rosal.
	 */
	@Column(name ="color", length=30, nullable=false )
	private String color;
	
	/**
	 * variedad que encapsula el tipo de rosal.
	 */
	@Column(name ="perfumada", length=2, nullable=false )
	private String perfumada;
	
	/**
	 * Propiedad que encapsula la altura del rosal.
	 */
	@Column(name ="altura", length=30, nullable=false )
	private String altura;
	
	/**
	 * Propiedad que encapsula el nombre de la imagen asociada.
	 */
	@Column(name ="nombre_imagen", length=40, unique=true )
	private String nombreImagen;
	
	/**
	 * Propiedad que encapsula la imagen del rosal.
	 */
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name ="id_imagen")
	@JsonIgnore
	Imagen imagen;
	
	/**
	 * Propiedad que encapsula la variedad del rosal.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="variedad")
	private Variedad variedad;
	
	/**
	 * propiedad que encapsula las unidades existentes en almacen de un rosal.
	 */
	@Column(name ="cantidad")
	private int cantidad;

	public Rosal() {
		super();
	}
	
	// Getter y setter.
	
	public int getIdRosal() {
		return idRosal;
	}

	public void setIdRosal(int idRosal) {
		this.idRosal = idRosal;
	}

	public String getNombreRosal() {
		return nombreRosal;
	}

	public void setNombreRosal(String nombreRosal) {
		this.nombreRosal = nombreRosal;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPerfumada() {
		return perfumada;
	}

	public void setPerfumada(String perfumada) {
		this.perfumada = perfumada;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}


	public Variedad getVariedad() {
		return variedad;
	}

	public void setVariedad(Variedad variedad) {
		this.variedad = variedad;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
	
}
