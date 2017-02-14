package es.pdv.daw.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import es.pdv.daw.proyect.beans.ClientValidate;
import es.pdv.daw.proyect.entity.Cliente;
import es.pdv.daw.proyect.services.ClientesService;

/**
 * Clase controlador de peticiones ajax del menu clientes.
 * @author jabd
 */
@Controller
@ResponseBody
public class ClientesController {

	/**
	 * Propiedad que encapsula el objeto de gestion de servicios de clientes.
	 */
	@Autowired
	private ClientesService clientesService;

	/**
	 * Metodo que devuelve una lista con los clientes registrados.
	 * 
	 * @param clientValidate
	 * @return
	 */
	@RequestMapping(value = "dameListaClientes", method = RequestMethod.GET)
	public ClientValidate dameListaClientes(ClientValidate clientValidate) {

		return clientesService.getClientList(clientValidate);
	}

	/**
	 * Metodo que registra un cliente.
	 * 
	 * @param clientValidate
	 * @param cliente
	 * @return
	 */
	@RequestMapping(value = "registraCliente", method = RequestMethod.POST)
	public ClientValidate registraCliente(ClientValidate clientValidate, Cliente cliente) {

		return clientesService.saveClient(clientValidate, cliente);
	}

	/**
	 * Metodo que actualiza un cliente.
	 * 
	 * @param clientValidate
	 * @param cliente
	 * @return
	 */
	@RequestMapping(value = "actualizaCliente", method = RequestMethod.POST)
	public ClientValidate actualizaCliente(ClientValidate clientValidate, Cliente cliente) {

		return clientesService.updateClient(clientValidate, cliente);
	}

	/**
	 * Metodo que elimina un cliente.
	 * 
	 * @param clientValidate
	 * @param cliente
	 * @return
	 */
	@RequestMapping(value = "eliminaCliente", method = RequestMethod.POST)
	public ClientValidate eliminaCliente(ClientValidate clientValidate, Cliente cliente) {

		return clientesService.deleteClient(clientValidate, cliente);
	}

}
