package es.pdv.daw.proyect.services;

import org.springframework.web.multipart.MultipartFile;
import es.pdv.daw.proyect.beans.EmpresaValidate;
import es.pdv.daw.proyect.entity.Empresa;
import es.pdv.daw.proyect.entity.Imagen;

/**
 * Interface de gestion de procesos de empresa.
 * @author mint
 *
 */
public interface EmpresaService {
	
	// Metodos documentados en las clases que implementas la interface.
	
	EmpresaValidate dameDatosEmpresa(EmpresaValidate empresaValidate);

	EmpresaValidate actualizaDatosEmpresa(EmpresaValidate empresaValidate, Empresa empresa, Imagen imagen,
			MultipartFile partImg);

	

}
