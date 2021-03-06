package es.pdv.daw.proyect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Clase controlador que procesa la carga de fragmentos del menu principal.
 * 
 * @author jabd
 *
 */
@Controller
public class LoadController {

	/**
	 * Metodo que carga el fragmento del menu administracion.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loadAdministracion")
	public String loadAdministracion(Model model) {

		model.addAttribute("view", "administracion");
		model.addAttribute("fragment", "administracion");

		return "home";
	}

	/**
	 * Metodo que carga el fragmento del menu empresa.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loadDatosEmpresa")
	public String loadDatosEmpresa(Model model) {

		model.addAttribute("view", "empresa");
		model.addAttribute("fragment", "empresa");

		return "home";
	}

	/**
	 * Metodo que carga el fragmento de la pagina de inicio.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loadInicio")
	public String loadInicio(Model model) {

		model.addAttribute("view", "slide");
		model.addAttribute("fragment", "carousel");

		return "home";
	}

	/**
	 * Metodo que carga el fragmento del menu rosales.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loadRosales")
	public String loadRosales(Model model) {

		model.addAttribute("view", "rosales");
		model.addAttribute("fragment", "rosales");

		return "home";
	}

	/**
	 * Metodo que carga el fragmento del menu variedades.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loadVaridades")
	public String loadVariedades(Model model) {

		model.addAttribute("view", "variedades");
		model.addAttribute("fragment", "variedades");

		return "home";
	}

	/**
	 * Metodo que carga el fragmento del menu nuevo pedido.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loadNuevoAlbaran")
	public String loadNuevoAlbaran(Model model) {

		model.addAttribute("view", "albaranes");
		model.addAttribute("fragment", "albaranes");

		return "home";
	}

	/**
	 * Metodo que carga el fragmento del menu lista pedidos.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loadAlbaranes")
	public String loadAlbaranes(Model model) {

		model.addAttribute("view", "listaAlbaranes");
		model.addAttribute("fragment", "listaAlbaranes");

		return "home";
	}

	/**
	 * Metodo que carga el fragmento del menu clientes.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loadClientes")
	public String loadClientes(Model model) {

		model.addAttribute("view", "clientes");
		model.addAttribute("fragment", "clientes");

		return "home";
	}

	/**
	 * Metodo que carga el fragmento del menu existencias.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loadExistencias")
	public String loadExistencias(Model model) {

		model.addAttribute("view", "existencias");
		model.addAttribute("fragment", "existencias");

		return "home";
	}
	
	
	/**
	 * Metodo que carga el fragmento del menu existencias.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "errorSistema",method = RequestMethod.POST)
	public String errorSistema() {

		System.out.println("metodo error.");
		

		return "error";
	}

}
