package es.pdv.daw.proyect.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import es.pdv.daw.proyect.entity.Variedad;

/**
 * Clase DAO de la tabla variedades.
 * 
 * @author jabd.
 *
 */
public interface VariedadRepository extends JpaRepository<Variedad, Integer> {

	/**
	 * Metodo que devuelve la variedad con el nombre suministrado por parametro.
	 * 
	 * @param nombreVariedad
	 * @return
	 */
	Variedad findByNombreVariedad(String nombreVariedad);

	/**
	 * Metodo que devuelve el numero de variedades con el nombre suministrado
	 * por parametro.
	 * 
	 * @param nombreVariedad
	 * @return
	 */
	int countByNombreVariedad(String nombreVariedad);

}
