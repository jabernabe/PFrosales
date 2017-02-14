package es.pdv.daw.proyect.beans;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase de proceso de peticiones ajax de gestion de imagenes de rosales.
 * 
 * @author jabd
 *
 */
@Component
public class BeanImagen implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Propiedad que encapsula la imagen codificada en base64.
	 */
	@JsonProperty
	private String image;

	/**
	 * Propiedad que encapsula el nombre de la imagen.
	 */
	private String nombreImagen;

	/**
	 * Constructor sin parametros.
	 */
	public BeanImagen() {
		super();

	}

	// METODOS SETTER Y GETTER.

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

}
