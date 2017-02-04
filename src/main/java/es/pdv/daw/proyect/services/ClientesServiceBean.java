package es.pdv.daw.proyect.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.pdv.daw.proyect.beans.ClientValidate;
import es.pdv.daw.proyect.dao.ClientesRepository;
import es.pdv.daw.proyect.entity.Cliente;

/**
 * Clase que gestiona los servicios de acceso a datos referentes a la gestion de clientes.
 * @author jabd
 *
 */
@Service
public class ClientesServiceBean implements ClientesService {

	/**
	 * Propiedad que encapsula la instancia de Logger de la aplicacion
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Propiedad que encapsula el objeto de acceso a datos.
	 */
	@Autowired
	ClientesRepository clientesRepository;
	
	/**
	 * Constructor sin parametros.
	 */
	public ClientesServiceBean() {
		super();
		
	}
	
	/**
	 * Metodo que devuelve una lista con los clientes registrados.
	 */
	@Override
	public ClientValidate getClientList(ClientValidate clientValidate) {
		
		List<Cliente> listaClientes = clientesRepository.findAll();
		
		if (listaClientes.isEmpty()){
			
			clientValidate.setExistClientes(false);
			clientValidate.setMessage("No existen clientes registrados");
		}
		else{
			clientValidate.setExistClientes(true);
			clientValidate.setMessage("Lista obtenida correctamente");
			clientValidate.setListaClientes(listaClientes);		
		}
		
		return clientValidate;
	}
	
	
}
