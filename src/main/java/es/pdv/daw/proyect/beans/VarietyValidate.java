package es.pdv.daw.proyect.beans;

import java.util.List;
import org.springframework.stereotype.Component;
import es.pdv.daw.proyect.entity.Variedad;

/**
 * Clase de proceso de datos de las peticiones ajax del submenu variedades.
 * 
 * @author jabd
 *
 */
@Component
public class VarietyValidate {

	/**
	 * Propiedad que encapsula el mensaje con el resultado de la transaccion.
	 */
	private String message;

	/**
	 * Propiedad que encapsula si la transaccion ha terminado
	 * satisfactoriamente.
	 */
	private boolean variedadExist;

	/**
	 * Propiedad que encapsula la lista de variedades registradas.
	 */
	private List<Variedad> listaVariedades;

	/**
	 * Constructor sin parametros.
	 */
	public VarietyValidate() {
		super();

	}

	// METODOS SETTER Y GETTER.

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isVariedadExist() {
		return variedadExist;
	}

	public void setVariedadExist(boolean variedadExist) {
		this.variedadExist = variedadExist;
	}

	public List<Variedad> getListaVariedades() {
		return listaVariedades;
	}

	public void setListaVariedades(List<Variedad> listaVariedades) {
		this.listaVariedades = listaVariedades;
	}

}
