package es.pdv.daw.proyect.beans;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class BeanImagen implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@JsonProperty
    private String image;
	
	private String nombreImagen;
	
	

    public BeanImagen() {
		super();
		
	}

	public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}
    
    
}
