package es.pdv.daw.proyect.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


/**
 * Entidad que representa la tabla rosales de la BD.
 * @author jabd.
 *
 */
@Entity
@Table(name="imagenes")
public class Imagen implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * identificador de imagen.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idImagen;
	
	/**
	 * propiedad que encapsula el nombre de la imagen.
	 */
	@Column(name ="nombre_imagen", length=30, nullable=false, unique=true )
	private String nombreImagen;
	
	/**
	 * Propiedad que encapsula la imagen del rosal.
	 */
	@Lob
	@Column(name ="imagen")
	private byte[] imagen;
	
	/**
	 * Constructor por defecto
	 */
	public Imagen() {
		super();
	}

	/**
	 * Constructor con parametros.
	 * @param idImagen
	 * @param nombreImagen
	 * @param imagen
	 */
	public Imagen(int idImagen, String nombreImagen, byte[] imagen) {
		super();
		this.idImagen = idImagen;
		this.nombreImagen = nombreImagen;
		this.imagen = imagen;
	}
	
	// Metodos getter y setter.
	
	public int getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
