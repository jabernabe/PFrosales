package es.pdv.daw.proyect.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import es.pdv.daw.proyect.entity.Cliente;


public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

	Cliente findByIdCliente(int idCliente);

	int countByNombreCliente(String nombreCliente);

	int countByIdentificador(String identificador);

	List<Cliente> findAllByOrderByNombreClienteAsc();

	

}
