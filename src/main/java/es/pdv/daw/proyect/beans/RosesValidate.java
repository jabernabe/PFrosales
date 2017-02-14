package es.pdv.daw.proyect.beans;

import org.springframework.stereotype.Component;

/**
 * Clase de proceso de datos de peticiones ajax del submenu rosales.
 * 
 * @author jabd.
 *
 */
@Component
public class RosesValidate {

	/**
	 * Propiedad que encapsula el mensaje tras la transaccion.
	 */
	private String message;

	/**
	 * Propiedad que encapsula el resultado de la transaccion.
	 */
	private boolean existRosal;

	/**
	 * Constructor sin parametros.
	 */
	public RosesValidate() {
		super();

	}

	// METODOS SETTER Y GETTER.

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isExistRosal() {
		return existRosal;
	}

	public void setExistRosal(boolean existRosal) {
		this.existRosal = existRosal;
	}

}
