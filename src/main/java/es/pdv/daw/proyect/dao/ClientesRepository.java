package es.pdv.daw.proyect.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import es.pdv.daw.proyect.entity.Cliente;

/**
 * Interface DAO de la tabla clientes.
 * 
 * @author jabd
 *
 */
public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

	/**
	 * Metodo que devuelve el cliente con el identificador suministrado por
	 * parametro.
	 * 
	 * @param idCliente
	 * @return
	 */
	Cliente findByIdCliente(int idCliente);

	/**
	 * Metodo que devuelve el numero de clientes con el nombre suministrado por
	 * parametros.
	 * 
	 * @param nombreCliente
	 * @return
	 */
	int countByNombreCliente(String nombreCliente);

	/**
	 * Metodo que devuelve el numero de clientes con el identificador
	 * suministrado por parametro.
	 * 
	 * @param identificador
	 * @return
	 */
	int countByIdentificador(String identificador);

	/**
	 * Metodo que devuelve una lista ordenada alfabeticamente en orden
	 * ascendente de los clientes registrados.
	 * 
	 * @return
	 */
	List<Cliente> findAllByOrderByNombreClienteAsc();

	/**
	 * Metodo que devuelve el cliente con el identificador suministrado
	 * 
	 * @param identificador
	 * @return
	 */
	Cliente findByIdentificador(String identificador);

	/**
	 * Metodo que devuelve el cliente con nombre suministrado por parametro.
	 * 
	 * @param nombreCliente
	 * @return
	 */
	Cliente findByNombreCliente(String nombreCliente);

	/**
	 * Metodo que devuelve el numero de clientes con el identificador
	 * suministrado pro parametro.
	 * 
	 * @param idCliente
	 * @return
	 */
	int countByIdCliente(int idCliente);

}
