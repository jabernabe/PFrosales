package es.pdv.daw.proyect.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import es.pdv.daw.proyect.entity.Albaran;

/**
 * Interface DAO de la tabla albaranes.
 * 
 * @author jabd
 *
 */
public interface AlbaranesRepository extends JpaRepository<Albaran, Integer> {

	/**
	 * Metodo que devuelve el albaran con el identificador suminstrado.
	 * 
	 * @param idAlbaran
	 * @return
	 */
	Albaran findByIdAlbaran(int idAlbaran);

	/**
	 * Metodo que devuelve el numero de albaranes con el identificador
	 * suministrado.
	 * 
	 * @param idAlbaran
	 * @return
	 */
	int countByIdAlbaran(int idAlbaran);
}
