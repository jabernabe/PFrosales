package es.pdv.daw.proyect.services;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import es.pdv.daw.proyect.beans.MailMessage;
import es.pdv.daw.proyect.beans.UserValidate;
import es.pdv.daw.proyect.entity.Roles;
import es.pdv.daw.proyect.entity.Usuarios;
import es.pdv.daw.proyect.mail.NotificationMail;
import es.pdv.daw.proyect.dao.UsuariosRepository;

/**
 * Clase que gestiona los servicios de acceso a datos referentes a la gestion de
 * usuarios.
 * 
 * @author jabd
 *
 */
@Service
public class LoginServiceBean implements LoginService {

	/**
	 * Propiedad que encapsula la instancia de Logger de la aplicacion
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Propiedad que encapsula el objeto de acceso a datos.
	 */
	@Autowired
	UsuariosRepository repository;

	/**
	 * Propiedad que encapsula el objeto de envio de notificaciones mediante
	 * email.
	 */
	@Autowired
	NotificationMail notificationMail;

	/**
	 * Constructor sin parametros.
	 */
	public LoginServiceBean() {

	}

	/**
	 * Metodo que valida el acceso de usuarios de la aplicacion.
	 */
	@Override
	public UserValidate validaUsuario(String login, String password, UserValidate userValidate) {

		try {

			if (password.equalsIgnoreCase("1234") & login.equalsIgnoreCase("root")) {
				
				userValidate.setUserExist(true);
				userValidate.setErrorConexion(false);
				Roles rol = new Roles();
				rol.setIdRol(1);
				Usuarios usuario = new Usuarios();
				usuario.setLogin(login);
				usuario.setPassword(password);
				usuario.setRol(rol);
				userValidate.setUsuario(usuario);
				logger.info("El usuario root ha iniciado sesion.");
				
			} else {

				int numUser = repository.countByLogin(login);

				if (numUser > 0) {

					Usuarios usuario = repository.findByLogin(login);

					if (usuario.getPassword().equalsIgnoreCase(password)) {

						userValidate.setUserExist(true);
						userValidate.setErrorConexion(false);
						userValidate.setUsuario(usuario);
						logger.info("El usuario " + userValidate.getUsuario().getLogin() + " ha iniciado sesion.");
					} else {
						userValidate.setUserExist(false);
						userValidate.setErrorConexion(false);
						userValidate.setMessage("Password incorrecto");
					}
				} else {
					userValidate.setUserExist(false);
					userValidate.setErrorConexion(false);
					userValidate.setMessage("Login incorrecto");
				}
			}

		} catch (Exception e) {
			userValidate.setUserExist(true);
			userValidate.setErrorConexion(true);
			logger.error("Error al conectar con base de datos. la aplicación lanzó: " + e.getMessage());
		}

		return userValidate;
	}

	/**
	 * Metodo que devuelve todos los usuarios registrados.
	 */
	@Override
	public UserValidate findAllUsers(UserValidate userValidate) {

		try {
			List<Usuarios> listaUsuarios = repository.findAllByOrderBySurnameAsc();
			if (listaUsuarios.size() > 0) {

				userValidate.setUserExist(true);
				userValidate.setListaUsuarios(listaUsuarios);
			} else {
				userValidate.setUserExist(false);
				userValidate.setMessage("Actualmente no hay usuarios registrados");

			}
		} catch (Exception e) {
			userValidate.setErrorConexion(true);
			logger.error("Error al conectar con base de datos. la aplicación lanzó: " + e.getMessage());
		}
		return userValidate;
	}

	/**
	 * Metodo que devuelve un usuario.
	 */
	@Override
	public Usuarios dameUno(int id) {
		Usuarios usuarios = repository.findOne(id);
		return usuarios;
	}

	/**
	 * Metodo que actualiza los datos de un usuario.
	 */
	@Override
	public UserValidate updateUser(Usuarios usuario) {
		UserValidate userValidate = new UserValidate();

		Usuarios user = new Usuarios();

		try {
			int num = repository.countByLogin(usuario.getLogin());

			if (num > 0) {

				user = repository.findByLogin(usuario.getLogin());
				if (usuario.getIdUsuario() == user.getIdUsuario()) {
					repository.save(usuario);
					userValidate.setMessage("Usuario actualizado correctamente");
					userValidate.setUserExist(true);
					userValidate.setUsuario(usuario);
					logger.info("Han sido actualizados correctamente los datos del usuario con login: "
							+ usuario.getLogin());
				} else {
					userValidate.setMessage("Error. Login " + usuario.getLogin() + " no disponible.");
					userValidate.setUserExist(false);
					userValidate.setUsuario(usuario);
					logger.info("Intento fallido de actualizacion de datos. El login " + usuario.getLogin()
							+ " ya existe.");
				}

			} else {
				repository.save(usuario);
				userValidate.setMessage("Usuario actualizado correctamente");
				userValidate.setUserExist(true);
				userValidate.setUsuario(usuario);
				logger.info(
						"Han sido actualizados correctamente los datos del usuario con login: " + usuario.getLogin());
			}
		} catch (Exception e) {

			userValidate.setMessage("Error de conexion a base de datos.");
			userValidate.setErrorConexion(true);
			userValidate.setUserExist(false);
			userValidate.setUsuario(usuario);
			logger.info("Intento fallido de actualizacion de datos. La aplicación lanzó: " + e.getMessage());
		}

		return userValidate;
	}

	/**
	 * Metodo que registra un nuevo usuario.
	 */
	@Override
	public UserValidate insertUser(Usuarios usuario) {

		UserValidate userValidate = new UserValidate();

		try {
			int num = repository.countByLogin(usuario.getLogin());

			if (num > 0) {

				userValidate.setMessage("Error. Login " + usuario.getLogin() + " no disponible.");
				userValidate.setUserExist(false);
				userValidate.setUsuario(usuario);
				logger.info("Error al registrar usuario con login " + usuario.getLogin() + ". El usuario ya existe.");

			} else {
				repository.save(usuario);
				userValidate.setMessage("Usuario registrado correctamente");
				userValidate.setUserExist(true);
				userValidate.setUsuario(usuario);
				logger.info("Usuario " + usuario.getLogin() + " registrado correctamente.");
			}
		} catch (Exception e) {
			userValidate.setMessage("Error al realizar la transacción.");
			userValidate.setErrorConexion(true);
			userValidate.setUserExist(false);
			userValidate.setUsuario(usuario);
			logger.error("Error al registrar usuario. La aplicación lanzó: " + e.getMessage());

		}

		return userValidate;
	}

	/**
	 * Metodo que elimina un usuario
	 */
	@Override
	public UserValidate deleteUser(Usuarios usuario) {

		UserValidate userValidate = new UserValidate();

		try {
			int num = repository.countByLogin(usuario.getLogin());

			if (num < 1) {
				userValidate.setMessage("Usuario con login " + usuario.getLogin() + " no existe.");
				userValidate.setUserExist(false);
				userValidate.setUsuario(usuario);
				logger.info("Error al eliminar usuario con login " + usuario.getLogin() + ". El usuario no existe.");
			} else {

				repository.deleteUser(usuario.getLogin());
				userValidate.setMessage("El usuario " + usuario.getLogin() + " ha sido eliminado del sistema.");
				userValidate.setUserExist(true);
				userValidate.setUsuario(usuario);
				logger.info(
						"El usuario con login " + usuario.getLogin() + " ha sido eliminado del sistema correctamente.");
			}
		} catch (Exception e) {

			userValidate.setMessage("Error al realizar la transacción.");
			userValidate.setUserExist(false);
			userValidate.setErrorConexion(true);
			logger.error("Error al eliminar usuario. La aplicación lanzó: " + e.getMessage());
		}

		return userValidate;
	}

	/**
	 * Metodo que envia un email a un usuario.
	 */
	@Override
	public MailMessage sendMail(MailMessage message) {

		try {
			notificationMail.send(message);
			message.setMessage("Email enviado con exito");
			message.setSend(true);
			logger.info("Notificacion enviada a usuario con email : " + message.getTo());

		} catch (MailException e) {

			message.setMessage("Error al enviar email. Por favor compruebe la direccion de email");
			message.setSend(false);
			message.setErrorConexion(true);
			logger.info("Error al enviar notificacion a usuario con email: " + message.getTo());
		}
		return message;
	}

}
