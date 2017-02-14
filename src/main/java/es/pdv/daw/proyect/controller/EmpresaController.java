package es.pdv.daw.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import es.pdv.daw.proyect.beans.EmpresaValidate;
import es.pdv.daw.proyect.entity.Empresa;
import es.pdv.daw.proyect.entity.Imagen;
import es.pdv.daw.proyect.services.EmpresaService;

/**
 * Clase controladora de peticiones ajax del submenu empresa.
 * @author mint
 *
 */
@Controller
@ResponseBody
public class EmpresaController {
	
	/**
	 * Propiedad que encapsula el objeto de acceso a base de datos .
	 */
	@Autowired
	private EmpresaService empresaService;
	
	/**
	 * Metodo que procesa la peticion de datos de empresa.
	 * @param message
	 * @return
	 */
	@RequestMapping(value="dameDatosEmpresa",method = RequestMethod.GET)
	public EmpresaValidate dameDatosEmpresa( 
			EmpresaValidate empresaValidate){
		
	return empresaService.dameDatosEmpresa(empresaValidate);	 
	}
	
	
	/**
	 * Metodo que procesa la peticion de actualizacion de datos de la empresa.
	 * @param empresaValidate
	 * @return
	 */
	@RequestMapping(value="actualizaDatos",method = RequestMethod.POST)
	public EmpresaValidate actualizaDatosEmpresa( 
			EmpresaValidate empresaValidate,
			Empresa empresa,
			Imagen imagen,
			@RequestParam(value="img") MultipartFile partImg){
		
	return empresaService.actualizaDatosEmpresa(empresaValidate, empresa, imagen, partImg );	 
	}

}
