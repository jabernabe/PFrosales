package es.pdv.daw.proyect.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import es.pdv.daw.proyect.beans.BeanImagen;
import es.pdv.daw.proyect.beans.RosesValidate;
import es.pdv.daw.proyect.beans.VarietyValidate;
import es.pdv.daw.proyect.entity.Imagen;
import es.pdv.daw.proyect.entity.Rosal;
import es.pdv.daw.proyect.entity.Variedad;
import es.pdv.daw.proyect.services.ProductService;

/**
 * Clase controladora de las peticiones ajax del menu productos.
 * @author jabd
 *
 */
@Controller
@ResponseBody
public class ProductController {

	/**
	 * Propiedad que encapsula el objeto de acceso a base de datos .
	 */
	@Autowired
	private ProductService productService;
	
	
	/**
	 * Metodo que registra un rosal en Bd.
	 * @param rosal
	 * @param rosesValidate
	 * @param imagen
	 * @param idVariedad
	 * @return
	 */
	@RequestMapping(value="insertaRosal",method = RequestMethod.POST)
	public RosesValidate insertaRosal(
			Rosal rosal,
			Imagen imagen,
			RosesValidate rosesValidate,
			@RequestParam(value="img") MultipartFile partImg,
			@RequestParam(value="idVariedad") int idVariedad){
		 
	return productService.insertaRosal(rosal, rosesValidate, partImg, idVariedad, imagen);	
	}
	
	
	/**
	 * Metodo que modifica un rosal en Bd.
	 * @param rosal
	 * @param rosesValidate
	 * @param imagen
	 * @param idVariedad
	 * @return
	 */
	@RequestMapping(value="modificaRosal",method = RequestMethod.POST)
	public RosesValidate modificaRosal(
			Rosal rosal,
			Imagen imagen,
			RosesValidate rosesValidate,
			@RequestParam(value="img") MultipartFile partImg,
			@RequestParam(value="idVariedad") int idVariedad){
		 
	return productService.modificaRosal(rosal, rosesValidate, partImg, idVariedad, imagen);	 
	}
	
	
	/**
	 * Metodo que devuelve una lista de todas las variedades registradas.
	 */
	@RequestMapping(value="dameVariedades",method = RequestMethod.GET)
	public VarietyValidate dameVariedades(VarietyValidate varietyValidate){
		
	return productService.dameVariedades(varietyValidate);	
	}
	
	
	/**
	 * Metodo que devuelve una lista con todos los rosales registrados.
	 * @return
	 */
	@RequestMapping(value="dameRosales",method = RequestMethod.GET)
	public RosesValidate getRosales(RosesValidate rosesValidate){
					
	return productService.findAllRoses(rosesValidate);	 
	}
	
	
	/**
	 * Metodo que recupera una imagen de servidor o base de datos.
	 * @param nombreImagen
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "saveImg")
	public BeanImagen handleFileUpload(@RequestParam("nombreImagen") String nombreImagen,
								   RedirectAttributes redirectAttributes) {
		
		BeanImagen beanImagen = productService.getImagen(nombreImagen);
		
		return beanImagen;
	}
	
	/**
	 * Metodo que elimina una rosal del sistema.
	 * @param rosal
	 * @return
	 */
	@RequestMapping(value="eliminaRosal",method = RequestMethod.GET)
	public RosesValidate eliminaRosal(
			Rosal rosal){
		
	return productService.deleteRose(rosal);	 
	}
	
	/**
	 * Metodo que registra una variedad.
	 * @param variedad
	 * @param varietyValidate
	 * @return
	 */
	@RequestMapping(value="registraVariedad",method = RequestMethod.POST)
	public VarietyValidate registraVariedad(
			Variedad variedad,
			VarietyValidate varietyValidate){
		 
	return productService.registraVariedad(variedad, varietyValidate);	 
	}
	
	/**
	 * Metodo que modifica una variedad
	 * @param variedad
	 * @param varietyValidate
	 * @return
	 */
	@RequestMapping(value="modificaVariedad",method = RequestMethod.POST)
	public VarietyValidate modificaVariedad(
			Variedad variedad,
			VarietyValidate varietyValidate){
		 
	return productService.modificaVariedad(variedad, varietyValidate);	 
	}
	
	
	/**
	 * Metodo que elimina una variedad
	 * @param variedad
	 * @return
	 */
	@RequestMapping(value="eliminaVariedad",method = RequestMethod.GET)
	public VarietyValidate eliminaVariedad(
			Variedad variedad){
		
	return productService.deleteVariedad(variedad);	 
	}
	
	/**
	 * Metodo que incrementa las existencias de un rosal.
	 * @param rosal
	 * @return
	 */
	@RequestMapping(value="aumentaExistencia",method = RequestMethod.POST)
	public RosesValidate aumentaExistencia(RosesValidate rosesValidate,
			Rosal rosal){
		
	return productService.incrementaExistencia(rosal, rosesValidate);	 
	}
	
	/**
	 * Metodo que incrementa las existencias de un rosal.
	 * @param rosal
	 * @return
	 */
	@RequestMapping(value="reduceExistencia",method = RequestMethod.POST)
	public RosesValidate reduceExistencia(RosesValidate rosesValidate,
			Rosal rosal){
		
	return productService.decrementaExistencia(rosal, rosesValidate);	 
	}
	
	
	/**
	 * Metodo que actualiza las existencias de un rosal.
	 * @param rosal
	 * @return
	 */
	@RequestMapping(value="actualizaExistencia",method = RequestMethod.POST)
	public RosesValidate actualizaExistencia(RosesValidate rosesValidate,
			Rosal rosal){
		
	return productService.actualizaExistencia(rosal, rosesValidate);	 
	}
		
}
