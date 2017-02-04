package es.pdv.daw.proyect.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import es.pdv.daw.proyect.entity.Usuarios;

/**
 * Interface dao de login de usuarios
 * @author jabd
 *
 */
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
	
	/**
	 * Metodo que devuelve el usuario con el login proporcionado
	 * @param login
	 * @return
	 */
	Usuarios findByLogin(String login);
	
	/**
	 * Metodo que devuelve el numero de usuarios con el login proporcionado
	 * @param login
	 * @return
	 */
	int countByLogin(String login);
	
	/**
	 * Metodo que devuelve todos los usuarios registrados.
	 * @return
	 */
	List<Usuarios> findAllByOrderBySurnameAsc();
	
	/**
	 * Metodo que elimina un usuario cuyo login coincida con el suministrado.
	 * @param login
	 */
	@Modifying
	@Transactional
	@Query("delete from Usuarios u where u.login = ?1")
	void deleteUser(String login);
	
	
}
