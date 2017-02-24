package es.pdv.daw.proyect.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.pdv.daw.proyect.beans.UserValidate;
import es.pdv.daw.proyect.entity.Usuarios;
import es.pdv.daw.proyect.services.LoginService;

/**
 * Controlador que procesa el acceso y cierre de sesion de usuarios.
 * 
 * @author jabd.
 *
 */
@Controller
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Propiedad que encapsula el objeto de acceso a DAO.
	 */
	@Autowired
	private LoginService loginService;

	/**
	 * Metodo de autenticacion de usuarios.
	 * 
	 * @param login
	 * @param password
	 * @param userValidate
	 * @param model
	 * @return
	 */
	@RequestMapping("login")
	public String loginUser(@RequestParam(value = "login") String login,
			@RequestParam(value = "password") String password, UserValidate userValidate, HttpSession session,
			Model model) {
		
		String vista = null;
		
		userValidate = loginService.validaUsuario(login, password, userValidate);

		if (userValidate.getErrorConexion()) {
			
			vista = "error";
			
			
		} else {
			
			if(userValidate.getUserExist()){
				
				session.setAttribute("usuario", userValidate.getUsuario().getLogin());
				session.setAttribute("rol", userValidate.getUsuario().getRol().getIdRol());
				model.addAttribute("view", "slide");
				model.addAttribute("fragment", "carousel");
				vista = "home";
				
			}
			else{
				
				model.addAttribute("mensaje", userValidate.getMessage());
				vista = "inicio";	
			}
			
		}
		
		return vista;
	}

	/**
	 * Metodo que redirecciona a la pagina de logueo de usuarios.
	 * 
	 * @param usuario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "inicio")
	public String inicio(Usuarios usuario, Model model) {

		return "redirect:/";
	}

	/**
	 * Metodo que invalida la sesion
	 * 
	 * @param usuario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "closeSession")
	public String closeSession(Usuarios usuario, Model model, HttpSession session) {

		session.invalidate();
		model.addAttribute("view", "slide");
		model.addAttribute("fragment", "carousel");
		return "home";
	}

	/**
	 * Metodo de cierre de sesion
	 * 
	 * @param usuario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "logout")
	public String logout() {

		return "redirect:/";
	}

}
