package es.pdv.daw.proyect.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import es.pdv.daw.proyect.entity.Rosal;

/**
 * Interface DAO de acceso a datos tabla rosales.
 * @author jabd
 *
 */
public interface RosalRepository extends JpaRepository<Rosal, Integer> {
	
	/**
	 * metodo que cuenta el numero de rosales con el nombre suministrado.
	 * @param nombreRosal
	 * @return
	 */
	int countByNombreRosal(String nombreRosal);

	List<Rosal> findAllByOrderByNombreRosalAsc();

	Rosal findByNombreRosal(String nombreRosal);

	Rosal findByIdRosal(int idRosal);
	
	@Modifying
	@Transactional
	@Query("delete from Rosal u where u.idRosal = ?1")
	void deleteRose(int idRosal);

	int countByIdRosal(int idRosal);

	

}
