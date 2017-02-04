package es.pdv.daw.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import es.pdv.daw.proyect.beans.ClientValidate;
import es.pdv.daw.proyect.services.ClientesService;

@Controller
@ResponseBody
public class ClientesController {
	
	/**
	 * Propiedad que encapsula el objeto de gestion de servicios de clientes.
	 */
	@Autowired
	private ClientesService clientesService;
	
	@RequestMapping(value="dameListaClientes",method = RequestMethod.GET)
	public ClientValidate dameListaClientes( 
			ClientValidate clientValidate){
		
	return clientesService.getClientList(clientValidate);	 
	}

}
