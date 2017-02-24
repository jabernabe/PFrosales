package es.pdv.daw.proyect.services;


import es.pdv.daw.proyect.beans.MailMessage;
import es.pdv.daw.proyect.beans.UserValidate;
import es.pdv.daw.proyect.entity.Usuarios;

/**
 * Interface de servicios de login y administracion de usuarios.
 * 
 * @author jabd
 *
 */
public interface LoginService {

	/**
	 * Metodo que valida un usurio.
	 * 
	 * @param login
	 * @param password
	 * @param userValidate
	 * @return
	 */
	UserValidate validaUsuario(String login, String password, UserValidate userValidate);

	/**
	 * Metodo que obtiene los usuarios registrados.
	 * @param userValidate 
	 * 
	 * @return
	 */
	UserValidate findAllUsers(UserValidate userValidate);

	/**
	 * Metodo que devuelve el usuario en el id suministrado.
	 * 
	 * @param id
	 * @return
	 */
	Usuarios dameUno(int id);

	/**
	 * Metodo que actualiza los datos de un usuario.
	 * 
	 * @param usuario
	 * @return
	 */
	UserValidate updateUser(Usuarios usuario);

	/**
	 * Metodo que registra un usuario.
	 * 
	 * @param usuario
	 * @return
	 */
	UserValidate insertUser(Usuarios usuario);

	/**
	 * Metodo que elimina un usuario.
	 * 
	 * @param usuario
	 * @return
	 */
	UserValidate deleteUser(Usuarios usuario);

	/**
	 * Metodo que realiza las notificaciones a usuarios mediante email.
	 * 
	 * @param message
	 * @return
	 */
	MailMessage sendMail(MailMessage message);

}
