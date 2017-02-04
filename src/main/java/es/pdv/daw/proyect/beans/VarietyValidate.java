package es.pdv.daw.proyect.beans;

import java.util.List;
import org.springframework.stereotype.Component;
import es.pdv.daw.proyect.entity.Variedad;

@Component
public class VarietyValidate {
	
	private String message;
	
	private boolean variedadExist;
	
	private List<Variedad> listaVariedades;

	public VarietyValidate() {
		super();

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isVariedadExist() {
		return variedadExist;
	}

	public void setVariedadExist(boolean variedadExist) {
		this.variedadExist = variedadExist;
	}

	public List<Variedad> getListaVariedades() {
		return listaVariedades;
	}

	public void setListaVariedades(List<Variedad> listaVariedades) {
		this.listaVariedades = listaVariedades;
	}
	
	
	
}
