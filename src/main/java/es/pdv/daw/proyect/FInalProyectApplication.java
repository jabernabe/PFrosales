package es.pdv.daw.proyect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Clase que inicia la aplicacion e inicia la configuracion de los distintos
 * componentes.
 * @author jabd
 */
@SpringBootApplication
public class FInalProyectApplication {
	
	/**
	 * Metodo maiñ que inicia la aplicacion.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FInalProyectApplication.class, args);
	}

	/**
	 * Bean de configuracion que permite objetos nulos en la json de las
	 * peticiones ajax.
	 * 
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
		return converter;
	}

}
