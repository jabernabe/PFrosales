package es.pdv.daw.proyect.beans;

import java.util.List;
import org.springframework.stereotype.Component;
import es.pdv.daw.proyect.entity.Cliente;

/**
 * Clase de proceso de datos de peticiones ajax del del menu clientes.
 * 
 * @author jabd
 *
 */
@Component
public class ClientValidate {

	/**
	 * Propiedad que encapsula el mensaje tras la transaccion.
	 */
	private String message;

	/**
	 * Propiedad que encapsula el resultado de la transaccion.
	 */
	private boolean existClientes;

	/**
	 * Propiedad que encapsula la lista de clientes registrados.
	 */
	private List<Cliente> listaClientes;

	/**
	 * Constructor sin parametros.
	 */
	public ClientValidate() {
		super();

	}

	// Metodos setter y getter.

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isExistClientes() {
		return existClientes;
	}

	public void setExistClientes(boolean existClientes) {
		this.existClientes = existClientes;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

}
