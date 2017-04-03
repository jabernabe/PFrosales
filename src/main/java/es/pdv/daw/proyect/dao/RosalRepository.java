package es.pdv.daw.proyect.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import es.pdv.daw.proyect.entity.Rosal;

/**
 * Interface DAO de acceso a datos tabla rosales.
 * 
 * @author jabd
 *
 */
public interface RosalRepository extends JpaRepository<Rosal, Integer> {

	/**
	 * metodo que cuenta el numero de rosales con el nombre suministrado.
	 * 
	 * @param nombreRosal
	 * @return
	 */
	int countByNombreRosal(String nombreRosal);

	/**
	 * Metodo que devuelve una lista de todos los rosales registrados ordenados
	 * por nombre alfabeticamente en orden ascendente.
	 * 
	 * @return
	 */
	List<Rosal> findAllByOrderByNombreRosalAsc();

	/**
	 * Metodoq que devuelve el rosal con el nombre suministrado por parametro.
	 * 
	 * @param nombreRosal
	 * @return
	 */
	Rosal findByNombreRosal(String nombreRosal);

	/**
	 * Metodo que devuelve el rosal con identificador suministrado por
	 * parametro.
	 * 
	 * @param idRosal
	 * @return
	 */
	Rosal findByIdRosal(int idRosal);

	/**
	 * Metodo que elimina de base de datos el rosal con el identificador
	 * suministrado por parametro.
	 * 
	 * @param idRosal
	 */
	@Modifying
	@Transactional
	@Query("delete from Rosal u where u.idRosal = ?1")
	void deleteRose(int idRosal);

	/**
	 * Metodo que devuelve el numero de rosales con el identificador
	 * suministrado por parametros.
	 * 
	 * @param idRosal
	 * @return
	 */
	int countByIdRosal(int idRosal);

	List<Rosal> findByEstadoOrderByNombreRosalAsc(String string);

}
