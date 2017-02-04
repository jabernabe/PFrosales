package es.pdv.daw.proyect.beans;

import java.util.List;
import org.springframework.stereotype.Component;
import es.pdv.daw.proyect.entity.Cliente;


@Component
public class ClientValidate {

	
	private String message;
	
	private boolean existClientes;
	
	private List<Cliente> listaClientes;

	
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
