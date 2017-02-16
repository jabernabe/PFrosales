package es.pdv.daw.proyect.beans;

import java.util.List;

import org.springframework.stereotype.Component;

import es.pdv.daw.proyect.entity.Rosal;

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
	 * Propiedad que encapsula el mensaje de error de conexion.
	 */
	private boolean errorConexion;
	
	/**
	 * Propiedad que encapsula la lista de rosales registrados.
	 */
	private List<Rosal> listaRosales;

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

	public List<Rosal> getListaRosales() {
		return listaRosales;
	}

	public void setListaRosales(List<Rosal> listaRosales) {
		this.listaRosales = listaRosales;
	}

	public boolean isErrorConexion() {
		return errorConexion;
	}

	public void setErrorConexion(boolean errorConexion) {
		this.errorConexion = errorConexion;
	}
	
	

}
