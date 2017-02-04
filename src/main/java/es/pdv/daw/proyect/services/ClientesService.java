package es.pdv.daw.proyect.services;

import es.pdv.daw.proyect.beans.ClientValidate;
import es.pdv.daw.proyect.entity.Cliente;

public interface ClientesService {

	ClientValidate getClientList(ClientValidate clientValidate);

	ClientValidate saveClient(ClientValidate clientValidate, Cliente cliente);

}
