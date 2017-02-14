package es.pdv.daw.proyect.services;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import es.pdv.daw.proyect.beans.EmpresaValidate;
import es.pdv.daw.proyect.dao.EmpresaRepository;
import es.pdv.daw.proyect.entity.Empresa;
import es.pdv.daw.proyect.entity.Imagen;

/**
 * Clase de servicio que gestiona los datos de la empresa.
 * 
 * @author alumno
 *
 */
@Service
public class EmpresaServiceBean implements EmpresaService {

	/**
	 * Propiedad que encapsula la instancia de Logger de la aplicacion
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Propiedad que encapsula el objeto de acceso a datos.
	 */
	@Autowired
	EmpresaRepository empresaRepository;

	/**
	 * Constructor sin parametros.
	 */
	public EmpresaServiceBean() {
		super();

	}

	/**
	 * Metodo que devuelve los datos de la empresa
	 */
	@Override
	public EmpresaValidate dameDatosEmpresa(EmpresaValidate empresaValidate) {

		try {
			int numRegistros = (int) empresaRepository.count();

			if (numRegistros > 0) {

				Empresa empresa = empresaRepository.findOne(1);
				empresaValidate.setEmpresa(empresa);
				empresaValidate.setExistEmpresa(true);
				empresaValidate.setMessage("Datos recuperados correctamente");

			} else {
				empresaValidate.setExistEmpresa(false);
				empresaValidate.setMessage("No existen registros en la base de datos.");
				logger.error("Error al consultar los datos de la empresa en base de datos.");
			}
		} catch (Exception e) {

			empresaValidate.setExistEmpresa(false);
			empresaValidate.setMessage("Error al realizar la transacción");
			logger.error("Error al consultar los datos de la empresa en base de datos." + "La aplicación lanzó: "
					+ e.getMessage());
		}

		return empresaValidate;
	}

	/**
	 * Metodo que actualiza los datos de la empresa.
	 */
	@Override
	public EmpresaValidate actualizaDatosEmpresa(EmpresaValidate empresaValidate, Empresa empresa, Imagen imagen,
			MultipartFile partImg) {

		try {
			Empresa emp = procesaImagen(partImg, empresa, imagen);
			empresaRepository.save(emp);
			empresaValidate.setEmpresa(emp);
			empresaValidate.setExistEmpresa(true);
			empresaValidate.setMessage("Datos actualizados correctamente");

		} catch (Exception e) {

			empresaValidate.setExistEmpresa(false);
			empresaValidate.setMessage("Error al realizar la transacción");
			logger.error("Error al actualizar los datos de la empresa." + "La aplicación lanzó: " + e.getMessage());
		}
		return empresaValidate;
	}

	/**
	 * Metodo que procesa la imagen.
	 * 
	 * @param partImagen
	 * @param empresa
	 * @param imagen
	 * @return
	 */
	private Empresa procesaImagen(MultipartFile partImagen, Empresa empresa, Imagen imagen) {

		try {
			byte[] imagenBlob = convertToByte(partImagen);

			// si no tiene tamaño, no hay foto
			int partImagenSize = (int) partImagen.getSize();

			if (partImagenSize > 0) {

				empresa.setImagenLogo(imagenBlob);
				updateOnServer(imagenBlob);
			}

		} catch (Exception e) {

			logger.error("Error al procesar la imagen. La aplicación lanzó: " + e.getMessage());

		}

		return empresa;
	}

	/**
	 * Metodo que actualiza la imagen del logo en el servidor.
	 * 
	 * @param imagenBlob
	 */
	private void updateOnServer(byte[] imagenBlob) {

		// Se obtiene la imagen de la bd y se guarda en el servidor.
		String path = System.getProperty("catalina.home") + File.separator + "work" + File.separator + "Tomcat"
				+ File.separator + "localhost" + File.separator;

		try {
			byte[] contenido = imagenBlob;
			File file = new File(path + "logoAlbaran.jpg");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(contenido);
			fileOutputStream.close();

		} catch (NullPointerException ex) {

			logger.error(ex.getMessage());
			ex.getMessage();

		} catch (FileNotFoundException e1) {

			logger.error("Archivo de logo no encontrado");
			e1.getMessage();

		} catch (IOException e1) {

			logger.error("Error al generar logo: " + e1.getMessage());
			
		}

	}

	/**
	 * Metodo que pasa un archivo tipo part a tipo byte
	 * 
	 * @param partImagen
	 * @return
	 */
	private byte[] convertToByte(MultipartFile partImagen) {

		byte[] foto = null;

		try {
			// si no tiene tamaño, no hay foto
			int imagenSize = (int) partImagen.getSize();

			if (imagenSize > 0) {

				foto = new byte[imagenSize];

				try (DataInputStream dis = new DataInputStream(partImagen.getInputStream())) {

					dis.readFully(foto);

				} catch (IOException e) {

					logger.error(e.getMessage());
				}
			}
		} catch (Exception e) {
			
			logger.error("Error al generar logo: La aplicación lanzó: " + e.getMessage());
		}
		return foto;
	}

	// Getter y setter.

	public EmpresaRepository getEmpresaRepository() {
		return empresaRepository;
	}

	public void setEmpresaRepository(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}

}
