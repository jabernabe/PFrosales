package es.pdv.daw.proyect.services;

import es.pdv.daw.proyect.beans.ClientValidate;
import es.pdv.daw.proyect.entity.Cliente;

/**
 * Interface de gestion de procesos de clientes
 * @author jabd
 *
 */
public interface ClientesService {
	
	// Metodos documentados en las clases que implementan la interface.
	
	ClientValidate getClientList(ClientValidate clientValidate);

	ClientValidate saveClient(ClientValidate clientValidate, Cliente cliente);

	ClientValidate updateClient(ClientValidate clientValidate, Cliente cliente);

	ClientValidate deleteClient(ClientValidate clientValidate, Cliente cliente);

}
