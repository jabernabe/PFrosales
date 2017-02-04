package es.pdv.daw.proyect.services;


import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import es.pdv.daw.proyect.beans.BeanImagen;
import es.pdv.daw.proyect.beans.RosesValidate;
import es.pdv.daw.proyect.beans.VarietyValidate;
import es.pdv.daw.proyect.entity.Imagen;
import es.pdv.daw.proyect.entity.Rosal;
import es.pdv.daw.proyect.entity.Variedad;

public interface ProductService {

	

	VarietyValidate dameVariedades(VarietyValidate varietyValidate);

	RosesValidate insertaRosal(Rosal rosal, RosesValidate rosesValidate, MultipartFile partImagen, int idVariedad,
			Imagen imagen);

	List<Rosal> findAllRoses();

	BeanImagen getImagen(String nombreImagen);

	RosesValidate modificaRosal(Rosal rosal, RosesValidate rosesValidate, MultipartFile partImagen, int idVariedad,
			Imagen imagen);

	RosesValidate deleteRose(Rosal rosal);

	VarietyValidate modificaVariedad(Variedad variedad, VarietyValidate varietyValidate);

	VarietyValidate deleteVariedad(Variedad variedad);

}
