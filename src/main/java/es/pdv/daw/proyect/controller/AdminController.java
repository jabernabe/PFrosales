package es.pdv.daw.proyect.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import es.pdv.daw.proyect.beans.MailMessage;
import es.pdv.daw.proyect.beans.UserValidate;
import es.pdv.daw.proyect.entity.Roles;
import es.pdv.daw.proyect.entity.Usuarios;
import es.pdv.daw.proyect.services.LoginService;

/**
 * Controlador que responde a las peticiones ajax del menu de administracion.
 * 
 * @author jabd
 *
 */
@Controller
@ResponseBody
public class AdminController {

	/**
	 * Propiedad que encapsula el objeto de acceso a base de datos .
	 */
	@Autowired
	private LoginService loginService;

	/**
	 * Metodo que devuelve los usuarios registrados en el sistema.
	 * 
	 * @param usuarios
	 * @return List<Usuarios>
	 */
	@RequestMapping(value = "dameUsuarios", method = RequestMethod.GET)
	public UserValidate dameUsuarios(UserValidate userValidate) {

		return loginService.findAllUsers(userValidate);
	}

	/**
	 * Metodo que actualiza un usuario.
	 * 
	 * @param userValidate
	 * @param usuario
	 * @param rol
	 * @param idRol
	 * @return
	 */
	@RequestMapping(value = "actualizaUsuario", method = RequestMethod.GET)
	public UserValidate actualizaUsuario(UserValidate userValidate, Usuarios usuario, Roles rol,
			@RequestParam(value = "idRol") int idRol) {

		rol.setIdRol(idRol);
		usuario.setRol(rol);

		return loginService.updateUser(usuario);
	}

	/**
	 * Metodo que crea un usuario.
	 * 
	 * @param userValidate
	 * @param usuario
	 * @param rol
	 * @param idRol
	 * @return
	 */
	@RequestMapping(value = "creaUsuario", method = RequestMethod.GET)
	public UserValidate creaUsuario(UserValidate userValidate, Usuarios usuario, Roles rol,
			@RequestParam(value = "idRol") int idRol) {

		rol.setIdRol(idRol);
		usuario.setRol(rol);

		return loginService.insertUser(usuario);
	}

	/**
	 * Metodo que elimina un usuario del sistema
	 * 
	 * @param userValidate
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "eliminaUsuario", method = RequestMethod.GET)
	public UserValidate eliminaUsuario(Usuarios usuario) {

		return loginService.deleteUser(usuario);
	}

	/**
	 * Metodo que procesa los envios de email.
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "notificaUsuario", method = RequestMethod.GET)
	public MailMessage NotificaUsuario(MailMessage message) {

		return loginService.sendMail(message);
	}

}
