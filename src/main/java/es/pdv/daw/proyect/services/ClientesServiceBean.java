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
	
	/**
	 * Metodo que registra un cliente.
	 */
	@Override
	public ClientValidate saveClient(ClientValidate clientValidate, Cliente cliente) {
		
		if (checkName(cliente.getNombreCliente())){
			
			clientValidate.setExistClientes(false);
			clientValidate.setMessage("Nombre de cliente registrado");
		}
		else{
			
			if (checkIdentificador(cliente.getIdentificador())){
				
				clientValidate.setExistClientes(false);
				clientValidate.setMessage("Identificador duplicado");
			}
			else{
				clientesRepository.saveAndFlush(cliente);
				clientValidate.setExistClientes(true);
				clientValidate.setMessage("Cliente registrado correctamente");	
			}
		}	
		
		return clientValidate;
	}
	
	/**
	 * Metodo que comprueba si el CIF o NIF estan registrados
	 * @param identificador
	 * @return
	 */
	private boolean checkIdentificador(String identificador) {
		boolean resultado = false;
		
		int num = clientesRepository.countByIdentificador(identificador);
		
		System.out.println(num);
		
		if (num>0){
			resultado = true;
		}
		else{
			resultado = false;
		}
			
		return resultado;
	}

	/**
	 * Metodo que comprueba si el nombre del cliente existe en base de datos.
	 * @param nombreCliente
	 * @return
	 */
	private boolean checkName(String nombreCliente) {
		
		boolean resultado = false;
		
		int num = clientesRepository.countByNombreCliente(nombreCliente);
		
		if (num>0){
			resultado = true;
		}
		else{
			resultado = false;
		}
		
		
		return resultado;
	}
	
	/**
	 * Metodo que actualiza los datos de un cliente.
	 */
	@Override
	public ClientValidate updateClient(ClientValidate clientValidate, Cliente cliente) {
		
		if (checkUpdateIdentificador(cliente)){
			
			if (checkUpdateName(cliente)){
				
				clientesRepository.saveAndFlush(cliente);
				clientValidate.setExistClientes(true);
				clientValidate.setMessage("Cliente Actualizado correctamente");	
			}
			else{
				
				clientValidate.setExistClientes(false);
				clientValidate.setMessage("Nombre de clinete no disponible.");		
			}
		}
		else{
			
			clientValidate.setExistClientes(false);
			clientValidate.setMessage("Numero CIF o NIF no disponible.");			
		}
			
		return clientValidate;
	}
	
	/**
	 * Metodo que comprueba si esta disponible el nombre de cliente.
	 * @param cliente
	 * @return
	 */
	private boolean checkUpdateName(Cliente cliente) {
		
		boolean resultado = false;
		
		int num = clientesRepository.countByNombreCliente(cliente.getNombreCliente());
		
		if (num>0){
			
			Cliente client = clientesRepository.findByNombreCliente(cliente.getNombreCliente());
			
			if (client.getIdCliente()==cliente.getIdCliente()){
				
				resultado = true;
			}
			else{
				
				resultado = false;
			}
		}
		else{
			
			resultado = true;
		}
		
		
		
		return resultado;
	}

	/**
	 * Metodo que comprueba si el identificador pertenece a otro cliente.
	 * @param cliente
	 * @return
	 */
	private boolean checkUpdateIdentificador(Cliente cliente) {
		
		boolean resultado = false;
		
		int num = clientesRepository.countByIdentificador(cliente.getIdentificador());
		
		if (num>0){
			
			Cliente client = clientesRepository.findByIdentificador(cliente.getIdentificador());
			
			if (client.getIdCliente()==cliente.getIdCliente()){
				
				resultado = true;
			}
			else{
				
				resultado = false;
			}
		}
		else{
			
			resultado = true;
		}
		
		
		return resultado;
	}
	
	
}
