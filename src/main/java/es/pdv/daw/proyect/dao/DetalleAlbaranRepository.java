package es.pdv.daw.proyect.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import es.pdv.daw.proyect.entity.Albaran;
import es.pdv.daw.proyect.entity.DetalleAlbaran;

/**
 * Interface DAO de la tabla detalle_albaran.
 * 
 * @author jabd
 *
 */
public interface DetalleAlbaranRepository extends JpaRepository<DetalleAlbaran, Integer> {

	/**
	 * Metodo que devuelve un lista con el detalle de un albaran.
	 * 
	 * @param albaran
	 * @return
	 */
	List<DetalleAlbaran> findByAlbaran(Albaran albaran);

	/**
	 * Metodo que devuelve el numero de albaranes con el identificador
	 * suministrado por parametro.
	 * 
	 * @param albaran
	 * @return
	 */
	int countByAlbaran(Albaran albaran);

	/**
	 * Metodo que devuelve el numero de detalles albaran con el identificador
	 * suministrado por parametro.
	 * 
	 * @param idDetalleAlbaran
	 * @return
	 */
	int countByIdDetalleAlbaran(int idDetalleAlbaran);

}
