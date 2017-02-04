package es.pdv.daw.proyect.beans;

import org.springframework.stereotype.Component;
import es.pdv.daw.proyect.entity.Empresa;

/**
 * Clase de validacion de datos de empresa.
 * @author jabd.
 *
 */
@Component
public class EmpresaValidate {
	
	/**
	 * Propiedad que encapsula el mensaje del proceso de validacion.
	 */
	private String message;
	
	/**
	 * Propiedad que encapsula si los datos de empresa se han obtenido correctamente.
	 */
	private boolean existEmpresa;
	
	/**
	 * Propiedad que encapsula el objeto con los datos de la empresa.
	 */
	private Empresa empresa;
	
	/**
	 * Constructor sin parametros.
	 */
	public EmpresaValidate() {
		super();
		
	}

	
	//Getter y setter.
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isExistEmpresa() {
		return existEmpresa;
	}

	public void setExistEmpresa(boolean existEmpresa) {
		this.existEmpresa = existEmpresa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	

}
