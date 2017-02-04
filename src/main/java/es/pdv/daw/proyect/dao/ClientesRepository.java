package es.pdv.daw.proyect.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import es.pdv.daw.proyect.entity.Cliente;


public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

	Cliente findByIdCliente(int idCliente);

	

}
