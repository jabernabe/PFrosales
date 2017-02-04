package es.pdv.daw.proyect.services;

import org.springframework.web.multipart.MultipartFile;

import es.pdv.daw.proyect.beans.EmpresaValidate;
import es.pdv.daw.proyect.entity.Empresa;
import es.pdv.daw.proyect.entity.Imagen;

public interface EmpresaService {

	EmpresaValidate dameDatosEmpresa(EmpresaValidate empresaValidate);

	EmpresaValidate actualizaDatosEmpresa(EmpresaValidate empresaValidate, Empresa empresa, Imagen imagen,
			MultipartFile partImg);

	

}
