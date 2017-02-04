package es.pdv.daw.proyect.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import es.pdv.daw.proyect.entity.Variedad;

public interface VariedadRepository extends JpaRepository<Variedad, Integer> {

	int countByIdVariedad(String nombreVariedad);

	Variedad findByNombreVariedad(String nombreVariedad);

	int countByNombreVariedad(String nombreVariedad);

}
