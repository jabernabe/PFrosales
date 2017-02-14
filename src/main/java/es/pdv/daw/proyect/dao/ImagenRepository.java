package es.pdv.daw.proyect.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import es.pdv.daw.proyect.entity.Imagen;

/**
 * Interface DAO de la tabla imagen.
 * 
 * @author jabd
 *
 */
public interface ImagenRepository extends JpaRepository<Imagen, Integer> {

	/**
	 * Metodo que devuelve la imagen cuyo nombre coincida con el suministrado
	 * por parametro.
	 * 
	 * @param nombreImagen
	 * @return
	 */
	Imagen findByNombreImagen(String nombreImagen);

	/**
	 * Metodo que devuelve el numero de imagenes con el nombre suministrado por
	 * parametros.
	 * 
	 * @param nombreImagen
	 * @return
	 */
	int countByNombreImagen(String nombreImagen);

	/**
	 * Metodo que elimina una imagen cuyo nombre coincida con el suministrado.
	 * 
	 * @param login
	 */
	@Modifying
	@Transactional
	@Query("delete from Imagen u where u.nombreImagen = ?1")
	void deleteImg(String nombreImagen);

}
