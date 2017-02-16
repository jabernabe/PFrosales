package es.pdv.daw.proyect.services;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import es.pdv.daw.proyect.beans.BeanImagen;
import es.pdv.daw.proyect.beans.RosesValidate;
import es.pdv.daw.proyect.beans.VarietyValidate;
import es.pdv.daw.proyect.entity.Imagen;
import es.pdv.daw.proyect.entity.Rosal;
import es.pdv.daw.proyect.entity.Variedad;

/**
 * Interface de procesos de gestion de productos
 * 
 * @author jabd
 *
 */
public interface ProductService {
	
	// Metodos documentados en las clases que implementan la interface.
	
	VarietyValidate dameVariedades(VarietyValidate varietyValidate);

	RosesValidate insertaRosal(Rosal rosal, RosesValidate rosesValidate, MultipartFile partImagen, int idVariedad,
			Imagen imagen);

	RosesValidate findAllRoses(RosesValidate rosesValidate);

	BeanImagen getImagen(String nombreImagen);

	RosesValidate modificaRosal(Rosal rosal, RosesValidate rosesValidate, MultipartFile partImagen, int idVariedad,
			Imagen imagen);

	RosesValidate deleteRose(Rosal rosal);

	VarietyValidate modificaVariedad(Variedad variedad, VarietyValidate varietyValidate);

	VarietyValidate deleteVariedad(Variedad variedad);

	RosesValidate incrementaExistencia(Rosal rosal, RosesValidate rosesValidate);

	RosesValidate decrementaExistencia(Rosal rosal, RosesValidate rosesValidate);

	RosesValidate actualizaExistencia(Rosal rosal, RosesValidate rosesValidate);

	VarietyValidate registraVariedad(Variedad variedad, VarietyValidate varietyValidate);

}
