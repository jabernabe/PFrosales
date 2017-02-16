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
 * Clase que gestiona los servicios de acceso a datos referentes a la gestion de
 * clientes.
 * 
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

		try {
			List<Cliente> listaClientes = clientesRepository.findAll();

			if (listaClientes.isEmpty()) {

				clientValidate.setExistClientes(false);
				clientValidate.setMessage("No existen clientes registrados");
			} else {
				clientValidate.setExistClientes(true);
				clientValidate.setMessage("Lista obtenida correctamente");
				clientValidate.setListaClientes(listaClientes);
			}

		} catch (Exception e) {

			clientValidate.setErrorConexion(true);
			clientValidate.setMessage("Error al realizar la transacción.");
			logger.error("Error al generar lista de clientes. La aplicación lanzó: " + e.getMessage());
		}

		return clientValidate;
	}

	/**
	 * Metodo que registra un cliente.
	 */
	@Override
	public ClientValidate saveClient(ClientValidate clientValidate, Cliente cliente) {

		try {
			if (checkName(cliente.getNombreCliente())) {

				clientValidate.setExistClientes(false);
				clientValidate.setMessage("Nombre de cliente registrado");
			} else {

				if (checkIdentificador(cliente.getIdentificador())) {

					clientValidate.setExistClientes(false);
					clientValidate.setMessage("Identificador duplicado");
				} else {
					clientesRepository.saveAndFlush(cliente);
					clientValidate.setExistClientes(true);
					clientValidate.setMessage("Cliente registrado correctamente");
				}
			}

		} catch (Exception e) {

			clientValidate.setExistClientes(false);
			clientValidate.setMessage("Error al realizar la transacción.");
			logger.error("Error al registrar cliente. La aplicación lanzó: " + e.getMessage());
		}

		return clientValidate;
	}

	/**
	 * Metodo que comprueba si el CIF o NIF estan registrados
	 * 
	 * @param identificador
	 * @return
	 */
	private boolean checkIdentificador(String identificador) {

		boolean resultado = false;

		try {
			int num = clientesRepository.countByIdentificador(identificador);

			System.out.println(num);

			if (num > 0) {
				resultado = true;
			} else {
				resultado = false;
			}
		} catch (Exception e) {

			logger.error("Error al comprobar CIF o NIF. La aplicación lanzó: " + e.getMessage());
		}

		return resultado;
	}

	/**
	 * Metodo que comprueba si el nombre del cliente existe en base de datos.
	 * 
	 * @param nombreCliente
	 * @return
	 */
	private boolean checkName(String nombreCliente) {

		boolean resultado = false;

		try {
			int num = clientesRepository.countByNombreCliente(nombreCliente);

			if (num > 0) {
				resultado = true;
			} else {
				resultado = false;
			}
		} catch (Exception e) {

			logger.error("Error al comprobar nombre de cliente. La aplicación lanzó: " + e.getMessage());
		}

		return resultado;
	}

	/**
	 * Metodo que actualiza los datos de un cliente.
	 */
	@Override
	public ClientValidate updateClient(ClientValidate clientValidate, Cliente cliente) {

		try {
			if (checkUpdateIdentificador(cliente)) {

				if (checkUpdateName(cliente)) {

					clientesRepository.saveAndFlush(cliente);
					clientValidate.setExistClientes(true);
					clientValidate.setMessage("Cliente Actualizado correctamente");
				} else {

					clientValidate.setExistClientes(false);
					clientValidate.setMessage("Nombre de clinete no disponible.");
				}
			} else {

				clientValidate.setExistClientes(false);
				clientValidate.setMessage("Numero CIF o NIF no disponible.");
			}
		} catch (Exception e) {

			clientValidate.setExistClientes(false);
			clientValidate.setMessage("Error al realizar la transacción.");
			logger.error("Error al actualizar cliente. La aplicación lanzó: " + e.getMessage());
		}

		return clientValidate;
	}

	/**
	 * Metodo que comprueba si esta disponible el nombre de cliente.
	 * 
	 * @param cliente
	 * @return
	 */
	private boolean checkUpdateName(Cliente cliente) {

		boolean resultado = false;

		try {
			int num = clientesRepository.countByNombreCliente(cliente.getNombreCliente());

			if (num > 0) {

				Cliente client = clientesRepository.findByNombreCliente(cliente.getNombreCliente());

				if (client.getIdCliente() == cliente.getIdCliente()) {

					resultado = true;
				} else {

					resultado = false;
				}
			} else {

				resultado = true;
			}
		} catch (Exception e) {

			logger.error("Error al comprobar disponibilidad nombre de cliente. La aplicación lanzó: " + e.getMessage());
		}

		return resultado;
	}

	/**
	 * Metodo que comprueba si el identificador pertenece a otro cliente.
	 * 
	 * @param cliente
	 * @return
	 */
	private boolean checkUpdateIdentificador(Cliente cliente) {

		boolean resultado = false;

		try {
			int num = clientesRepository.countByIdentificador(cliente.getIdentificador());

			if (num > 0) {

				Cliente client = clientesRepository.findByIdentificador(cliente.getIdentificador());

				if (client.getIdCliente() == cliente.getIdCliente()) {

					resultado = true;
				} else {

					resultado = false;
				}
			} else {

				resultado = true;
			}
		} catch (Exception e) {

			logger.error("Error al comprobar identificador de cliente. La aplicación lanzó: " + e.getMessage());
		}

		return resultado;
	}

	/**
	 * Metodo que elimina un cliente de la base de datos.
	 */
	@Override
	public ClientValidate deleteClient(ClientValidate clientValidate, Cliente cliente) {

		try {
			int num = clientesRepository.countByIdCliente(cliente.getIdCliente());

			if (num > 0) {
				clientesRepository.delete(cliente);
				clientValidate.setExistClientes(true);
				clientValidate.setMessage("Cliente eliminado correctamente");
			} else {
				clientValidate.setExistClientes(false);
				clientValidate.setMessage("Error al eliminar el cliente. El cliente no existe.");
			}
			
		} catch (Exception e) {

			clientValidate.setExistClientes(false);
			clientValidate.setMessage("Error al realizar la transacción.");
			logger.error("Error al eliminar cliente. La aplicación lanzó: " + e.getMessage());
		}

		return clientValidate;
	}

}
