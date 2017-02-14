package es.pdv.daw.proyect.services;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import es.pdv.daw.proyect.beans.BeanImagen;
import es.pdv.daw.proyect.beans.RosesValidate;
import es.pdv.daw.proyect.beans.VarietyValidate;
import es.pdv.daw.proyect.dao.ImagenRepository;
import es.pdv.daw.proyect.dao.RosalRepository;
import es.pdv.daw.proyect.dao.VariedadRepository;
import es.pdv.daw.proyect.entity.Imagen;
import es.pdv.daw.proyect.entity.Rosal;
import es.pdv.daw.proyect.entity.Variedad;

/**
 * Clase que implementa los procesos de gestion de variedades y rosales en la
 * bd.
 * 
 * @author jabd
 *
 */
@Service
public class ProductServiceBean implements ProductService {

	/**
	 * Propiedad que encapsula la instancia de Logger de la aplicacion.
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Propiedad que encapsula la ruta de recursos del servidor.
	 */
	private String path = System.getProperty("catalina.home") + File.separator + "work" + File.separator + "Tomcat"
			+ File.separator + "localhost" + File.separator;

	/**
	 * Propiedad que encapsula el objeto de acceso a datos tabla rosales.
	 */
	@Autowired
	RosalRepository rosalRepository;

	/**
	 * Propiedad que encapsula el objeto de acceso a datos tabla variedades.
	 */
	@Autowired
	VariedadRepository varRepository;

	/**
	 * Propiedad que encapsula el objeto de acceso a datos tabla imagenes.
	 */
	@Autowired
	ImagenRepository imagenRepository;

	/**
	 * Metodo que obtiene una lista con todas las variedades.
	 */
	@Override
	public VarietyValidate dameVariedades(VarietyValidate varietyValidate) {

		try {
			List<Variedad> lista = varRepository.findAll();

			if (lista.size() > 0) {

				varietyValidate.setMessage("Lista obtenida satisfactoriamente");
				varietyValidate.setVariedadExist(true);
				varietyValidate.setListaVariedades(lista);

			} else {
				varietyValidate.setMessage("No existen variedades actualmente.");
				varietyValidate.setVariedadExist(false);

			}
		} catch (Exception e) {

			varietyValidate.setMessage("Error al realizar la transaccion.");
			varietyValidate.setVariedadExist(false);
			logger.error("Error al obtener lista de variedades. El sistema lanzo: " + e.getMessage());
		}
		return varietyValidate;
	}

	/**
	 * Metodo que registra un rosal.
	 */
	@Override
	public RosesValidate insertaRosal(Rosal rosal, RosesValidate rosesValidate, MultipartFile partImagen,
			int idVariedad, Imagen imagen) {

		try {

			Variedad variedad = new Variedad();
			variedad.setIdVariedad(idVariedad);
			rosal.setVariedad(variedad);
			byte[] imagenBlob = convertToByte(partImagen);

			int countImg = imagenRepository.countByNombreImagen(partImagen.getOriginalFilename());

			if (countImg > 0) {

				rosesValidate.setExistRosal(false);
				rosesValidate.setMessage("Nombre de imagen de rosal ya registrado");
				logger.info("Intento fallido al registrar rosal con nombre " + rosal.getNombreRosal()
						+ ".El nombre de imagen ya existe.");
			} else {

				// si no tiene tamaño no hay foto.
				int partImagenSize = (int) partImagen.getSize();

				if (partImagenSize > 0) {

					imagen.setImagen(imagenBlob);
					imagen.setNombreImagen(partImagen.getOriginalFilename());
					rosal.setImagen(imagen);
					rosal.setNombreImagen(partImagen.getOriginalFilename());
				}

				int num = rosalRepository.countByNombreRosal(rosal.getNombreRosal());

				if (num > 0) {
					rosesValidate.setExistRosal(false);
					rosesValidate.setMessage("Nombre de rosal ya registrado");
					logger.info("Intento fallido al registrar rosal con nombre " + rosal.getNombreRosal()
							+ ".El rosal ya existe.");
				} else {
					rosalRepository.save(rosal);
					rosesValidate.setExistRosal(true);
					rosesValidate.setMessage("Rosal registrado con exito");
					logger.info("El rosal con nombre " + rosal.getNombreRosal() + " ha sido registrado con exito");
				}
			}
		} catch (Exception e) {

			rosesValidate.setExistRosal(false);
			rosesValidate.setMessage("Error al realizar la transacción");
			logger.info("Intento fallido al registrar rosal con nombre " + rosal.getNombreRosal() + ".El sistema lanzo:"
					+ e.getMessage());

		}
		return rosesValidate;
	}

	/**
	 * Metodo que modifica un rosal.
	 */
	@Override
	public RosesValidate modificaRosal(Rosal rosal, RosesValidate rosesValidate, MultipartFile partImagen,
			int idVariedad, Imagen imagen) {

		try {

			Rosal rosa = procesaImagenRosal(partImagen, rosal, imagen);
			Variedad variedad = new Variedad();
			variedad.setIdVariedad(idVariedad);
			rosa.setVariedad(variedad);

			int num = rosalRepository.countByNombreRosal(rosa.getNombreRosal());

			if (num > 0) { // hay un rosal con ese nombre

				boolean esMiRosal = compruebaRosal(rosa);
				
				// coincide el nombre de rosal con el rosal a actualizar?
				if (esMiRosal) { 

					boolean imagenCorrecta = compruebaImagen(rosa);

					if (imagenCorrecta) {
						deleteFromServer(rosa);
						// si el nombre de imagen pertence al mismo registro se actualiza.
						rosalRepository.save(rosa); 
						rosesValidate.setExistRosal(true);
						rosesValidate.setMessage("Rosal modificado con exito");
						logger.info("El rosal con nombre " + rosa.getNombreRosal() + " ha sido modificado con exito");
					} else {
						// si no. El nombre de imagen ya se esta utilizando.
						rosesValidate.setExistRosal(false); 
						rosesValidate.setMessage("Nombre de imagen de rosal ya registrada");
						logger.info("Intento fallido al modificar rosal con nombre " + rosa.getNombreRosal()
								+ ".El nombre de la imagen ya existe.");
					}
				} else {
					rosesValidate.setExistRosal(false);
					rosesValidate.setMessage("Nombre de rosal ya registrado");
					logger.info("Intento fallido al modificar rosal con nombre " + rosa.getNombreRosal()
							+ ".El rosal ya existe.");
				}
			} else {

				boolean imagenCorrecta = compruebaImagen(rosa);

				if (imagenCorrecta) {
					deleteFromServer(rosa);
					// si el nombre de imagenpertence al mismo registro actualiza.
					rosalRepository.save(rosa); 
					rosesValidate.setExistRosal(true);
					rosesValidate.setMessage("Rosal modificado con exito");
					logger.info("El rosal con nombre " + rosa.getNombreRosal() + " ha sido modificado con exito");
				} else {
					// si no. El nombre de imagen ya se esta utilizando.
					rosesValidate.setExistRosal(false); 
					rosesValidate.setMessage("Nombre de imagen de rosal ya registrada");
					logger.info("Intento fallido al modificar rosal con nombre " + rosa.getNombreRosal()
							+ ".El nombre de la imagen ya existe.");
				}
			}

		} catch (Exception e) {
			rosesValidate.setExistRosal(false);
			rosesValidate.setMessage("Error al realizar la transacción");
			logger.info("Intento fallido al modificar el rosal con nombre. La aplicacion lanzo: " + e.getMessage());
		}

		return rosesValidate;
	}

	/**
	 * Metodo que elimina una imagen del servidor.
	 * 
	 * @param rosa
	 */
	private void deleteFromServer(Rosal rosa) {

		try {
			Rosal rosalActual = rosalRepository.findByIdRosal(rosa.getIdRosal());

			if (rosa.getNombreImagen() != rosalActual.getNombreImagen()) {

				File file = new File(path + rosalActual.getNombreImagen());

				if (file.delete()) {
					logger.info("La imagen " + rosalActual.getNombreImagen() + " ha sido eliminada del servidor");
				}
			}

		} catch (Exception e) {

			logger.info("Error de transacción. La aplicación lanzó: " + e.getMessage());
		}

	}

	/**
	 * Metodo que comprueba que el nombre de imagen no este ya en uso.
	 * 
	 * @param rosa
	 * @return
	 */
	public boolean compruebaImagen(Rosal rosa) {

		boolean resultado = false;

		try {
			boolean existeImagen = existeImagen(rosa);
			
			// existe una imagen con el nombre suministrado en la base de datos?
			if (existeImagen) { 

				boolean esMiImagen = esMiImagen(rosa);

				if (esMiImagen) {

					resultado = true;
				} else {
					resultado = false;
				}
			} else {
				resultado = true;
			}

		} catch (Exception e) {

			logger.error("Error al comprobar imagen de rosal. La aplicación lanzó: " + e.getMessage());
		}

		return resultado;
	}

	/**
	 * Metodo que comprueba si la imagen registrada pertenece al rosal a
	 * actualizar
	 * 
	 * @param rosal
	 * @return
	 */
	public boolean esMiImagen(Rosal rosal) {

		boolean resultado = false;

		try {
			boolean isNull = isNombreImagenNull(rosal);

			if (isNull) {

				resultado = false;
			} else {

				Rosal rose = rosalRepository.findByIdRosal(rosal.getIdRosal());
				Imagen img = imagenRepository.findByNombreImagen(rosal.getNombreImagen());

				if (rose.getNombreImagen() == null) {

					resultado = false;
				} else {

					if (rose.getNombreImagen().equalsIgnoreCase(img.getNombreImagen())) {

						resultado = true;
					} else {

						resultado = false;
					}
				}
			}
		} catch (Exception e) {

			logger.error("Error al comprobar coincidencia en nombre de rosal. La aplicacion lanzó: " + e.getMessage());
		}

		return resultado;
	}

	/**
	 * Metodo que comprueba si el rosal a modificar posee una imagen asociada.
	 * 
	 * @param rosal
	 * @return
	 */
	public boolean isNombreImagenNull(Rosal rosal) {

		boolean resultado = false;

		try {
			if (rosal.getNombreImagen() == null) {

				resultado = true;
			} else {
				resultado = false;
			}
		} catch (Exception e) {

			logger.error("Error al comprabar nombre nulo de nombre de imagen. La aplicación lanzó:" + e.getMessage());
		}

		return resultado;
	}

	/**
	 * Metodo que comprueba si existe una imagen registrada con el mismo nombre.
	 * 
	 * @param rosal
	 * @return
	 */
	public boolean existeImagen(Rosal rosal) {

		boolean resultado = false;

		try {
			int numImages = imagenRepository.countByNombreImagen(rosal.getNombreImagen());

			if (numImages > 0) {
				resultado = true;
			} else {
				resultado = false;
			}
		} catch (Exception e) {

			logger.error("Error al comprobar si existe imagen de rosal. La aplicación lanzó: " + e.getMessage());
		}

		return resultado;
	}

	/**
	 * Metodo que comprueba si el rosal existente en base de datos coincide con
	 * el rosal que se pretende actualizar.
	 * 
	 * @param rosal
	 * @return
	 */
	public boolean compruebaRosal(Rosal rosal) {

		boolean resultado = false;

		try {
			Rosal rose = rosalRepository.findByNombreRosal(rosal.getNombreRosal());

			if (rosal.getIdRosal() == rose.getIdRosal()) {

				resultado = true;
			} else {
				resultado = false;
			}
		} catch (Exception e) {

			logger.error("Error al comprobar identificador de rosal. La aplicación lanzó: " + e.getMessage());
		}

		return resultado;
	}

	/**
	 * Metodo que procesa la imagen al actualizar registro de rosal.
	 * 
	 * @param partImagen
	 * @param rosal
	 * @param imagen
	 * @return
	 */
	private Rosal procesaImagenRosal(MultipartFile partImagen, Rosal rosal, Imagen imagen) {

		try {
			byte[] imagenBlob = convertToByte(partImagen);

			int partImagenSize = (int) partImagen.getSize(); // si no tiene tamaño,
			if (partImagenSize > 0) {

				int num = imagenRepository.countByNombreImagen(rosal.getNombreImagen());
				
				// Si ya existe la imagen se añade su id para actualizar la imagen
				if (num > 0) { 
					Imagen img = imagenRepository.findByNombreImagen(rosal.getNombreImagen());
					imagen.setIdImagen(img.getIdImagen());
				}
				// si no existe imagen el id sera generado.
				imagen.setImagen(imagenBlob);
				imagen.setNombreImagen(partImagen.getOriginalFilename());
				rosal.setImagen(imagen);
				rosal.setNombreImagen(partImagen.getOriginalFilename());
			} else {

				if (rosal.getNombreImagen() != "" || rosal.getNombreImagen() != null) {
					Rosal rosa = rosalRepository.findByNombreRosal(rosal.getNombreRosal());
				}
			}

		} catch (Exception e) {

			logger.error("Error al procesar imagen de rosal. La aplicación lanzó: " + e.getMessage());
		}

		return rosal;
	}

	/**
	 * Metodo que elimina una imagen de la bd.
	 * 
	 * @param rosal
	 */
	private void eliminaImagenes(String nombreImagen) {

		imagenRepository.deleteImg(nombreImagen);
	}

	/**
	 * Metodo que pasa un archivo tipo part a tipo byte.
	 * 
	 * @param partImagen
	 * @return
	 */
	private byte[] convertToByte(MultipartFile partImagen) {

		byte[] foto = null;

		try {
			// si no tiene tamaño, no hay foto.
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

			logger.error("Error al convertir la imagen a tipo byte. La aplicación lanzó: " + e.getMessage());
		}
		return foto;
	}

	/**
	 * Metodo que devuelve una lista de rosales.
	 */
	@Override
	public List<Rosal> findAllRoses() {

		List<Rosal> lista = null;

		try {

			lista = rosalRepository.findAllByOrderByNombreRosalAsc();

		} catch (Exception e) {

			logger.error("Error al obtener lista de rosales. La aplicación lanzó: " + e.getMessage());
		}

		return lista;
	}

	/**
	 * Metodo que devuelve una imagen.
	 */
	@Override
	public BeanImagen getImagen(String nombreImagen) {

		BeanImagen beanImagen = new BeanImagen();
		File archivo = new File(path + nombreImagen);

		try {
			if (archivo.exists()) {

				byte[] bytes = Files.readAllBytes(new File(path + nombreImagen).toPath());
				beanImagen.setImage(DatatypeConverter.printBase64Binary(bytes));
				beanImagen.setNombreImagen(nombreImagen);
				System.out.println("el archivo ya estaba en el servidor");

			} else {

				creaArchivo(nombreImagen);
				byte[] bytes = Files.readAllBytes(new File(path + nombreImagen).toPath());
				beanImagen.setImage(DatatypeConverter.printBase64Binary(bytes));
				beanImagen.setNombreImagen(nombreImagen);
				System.out.println("el archivo no estaba en el servidor y ha sido creado");

			}

		} catch (IOException e) {

			logger.error("Error al obtener la imagen. La aplicación lanzó: " + e.getMessage());
		}

		return beanImagen;
	}

	/**
	 * Metodo que recupera una imagen y la guarda en el servidor.
	 * 
	 * @param nombreImagen
	 */
	private void creaArchivo(String nombreImagen) {

		try {

			Imagen imagen = imagenRepository.findByNombreImagen(nombreImagen);
			byte[] contenido = imagen.getImagen();
			File file = new File(imagen.getNombreImagen());
			new FileOutputStream(file).write(contenido);
			InputStream is = new FileInputStream(file);
			if (file.length() > 0) {
				try {

					Files.copy(is, Paths.get(path, file.getName()));

				} catch (IOException | RuntimeException e) {

					logger.error("Error al subir el archivo " + file.getName() + " => " + e.getMessage());
				}
			} else {
				logger.error("Error al subir el archivo " + file.getName() + " . El archivo esta vacio.");
			}

		} catch (FileNotFoundException e1) {

			logger.error("Error al subir el archivo. No se encuentra el archivo.");

		} catch (IOException e1) {

			logger.error("Error: " + e1.getMessage());
		}

	}

	/**
	 * Metodo que elimina un rosal del sistema.
	 */
	@Override
	public RosesValidate deleteRose(Rosal rosal) {

		RosesValidate rosesValidate = new RosesValidate();

		try {
			int num = rosalRepository.countByNombreRosal(rosal.getNombreRosal());

			if (num > 0) {
				deleteFromServer(rosal);
				rosalRepository.delete(rosal.getIdRosal());
				rosesValidate.setExistRosal(true);
				rosesValidate.setMessage("Rosal eliminado con exito");
				logger.info("rosal con nombre " + rosal.getNombreRosal() + " eliminado correctamente.");
			} else {
				rosesValidate.setExistRosal(false);
				rosesValidate.setMessage("Error: el rosal no existe");
				logger.info("El rosal con nombre " + rosal.getNombreRosal() + " no se ha podido eliminar.");
			}
		} catch (Exception e) {
			rosesValidate.setExistRosal(false);
			rosesValidate.setMessage("Error al realizar la transacción");
			logger.info("El rosal no se ha podido eliminar. La aplicación lanzó: " + e.getMessage());

		}
		return rosesValidate;
	}

	/**
	 * Metodo que registra una variedad.
	 */
	@Override
	public VarietyValidate registraVariedad(Variedad variedad, VarietyValidate varietyValidate) {

		try {
			int num = varRepository.countByNombreVariedad(variedad.getNombreVariedad());

			if (num > 0) {

				varietyValidate.setVariedadExist(false);
				varietyValidate.setMessage("ERROR: Nombre de varidad existente.");
				logger.info("la variedad " + variedad.getNombreVariedad() + " ha sido modificada correctamente.");

			} else {

				varRepository.save(variedad);
				varietyValidate.setVariedadExist(true);
				varietyValidate.setMessage("Variedad " + variedad.getNombreVariedad() + " registrada correctamente");
				logger.info("la variedad " + variedad.getNombreVariedad() + " ha sido registrada correctamente.");
			}
		} catch (Exception e) {

			varietyValidate.setVariedadExist(false);
			varietyValidate.setMessage("Error al realizar la transaccion. No se ha podido conectar con base de datos.");
			logger.info("Error al registrar " + variedad.getNombreVariedad() + ". El sistema lanzo: " + e.getMessage());
		}

		return varietyValidate;
	}

	/**
	 * Metodo que modifica una variedad.
	 */
	@Override
	public VarietyValidate modificaVariedad(Variedad variedad, VarietyValidate varietyValidate) {

		try {
			int num = varRepository.countByNombreVariedad(variedad.getNombreVariedad());

			if (num > 0) {

				Variedad var = new Variedad();
				var = varRepository.findByNombreVariedad(variedad.getNombreVariedad());

				if (var.getIdVariedad() == variedad.getIdVariedad()) {

					varRepository.save(variedad);
					varietyValidate.setVariedadExist(true);
					varietyValidate
							.setMessage("Variedad " + variedad.getNombreVariedad() + " modificada correctamente");
					logger.info("la variedad " + variedad.getNombreVariedad() + " ha sido modificada correctamente.");

				} else {
					varietyValidate.setVariedadExist(false);
					varietyValidate.setMessage("ERROR: Nombre de varidad existente.");
					logger.info("la variedad " + variedad.getNombreVariedad()
							+ " no se ha modificado. La variedad ya existe.");
				}

			} else {

				varRepository.save(variedad);
				varietyValidate.setVariedadExist(true);
				varietyValidate.setMessage("Variedad " + variedad.getNombreVariedad() + " modificada correctamente");
				logger.info("la variedad " + variedad.getNombreVariedad() + " ha sido modificada correctamente.");
			}

		} catch (Exception e) {

			varietyValidate.setVariedadExist(false);
			varietyValidate.setMessage("Error al realizar la transacción");
			logger.info("la variedad no se ha podido modificar. La aplicación lanzó: " + e.getMessage());
		}

		return varietyValidate;
	}

	/**
	 * Metodo que elimina una variedad de base de datos.
	 */
	@Override
	public VarietyValidate deleteVariedad(Variedad variedad) {

		VarietyValidate varietyValidate = new VarietyValidate();

		try {

			int num = varRepository.countByNombreVariedad(variedad.getNombreVariedad());

			if (num > 0) {

				varRepository.delete(variedad.getIdVariedad());
				varietyValidate.setVariedadExist(true);
				varietyValidate.setMessage("variedad " + variedad.getNombreVariedad() + " eliminada con exito");
				logger.info("variedad con nombre " + variedad.getNombreVariedad() + " eliminada correctamente.");

			} else {
				varietyValidate.setVariedadExist(false);
				varietyValidate.setMessage("Error: La variedad no existe.");
				logger.info("variedad con nombre " + variedad.getNombreVariedad() + " no se ha podido eliminar.");
			}
		} catch (Exception e) {

			varietyValidate.setVariedadExist(false);
			varietyValidate.setMessage("Error al realizar la transacción");
			logger.info("Error: " + e.getMessage());

		}
		return varietyValidate;
	}

	/**
	 * Metodo que incrementa las existencias de un rosal.
	 */
	@Override
	public RosesValidate incrementaExistencia(Rosal rosal, RosesValidate rosesValidate) {

		try {
			int num = rosalRepository.countByIdRosal(rosal.getIdRosal());

			if (num > 0) {

				Rosal rosalActual = rosalRepository.findOne(rosal.getIdRosal());
				int existencias = rosalActual.getCantidad() + rosal.getCantidad();
				rosalActual.setCantidad(existencias);
				rosalRepository.save(rosalActual);
				rosesValidate.setExistRosal(true);
				rosesValidate.setMessage("Las existencias de " + rosalActual.getNombreRosal()
						+ " han sido incrementadas en " + rosal.getCantidad() + " unidades correctamente.");

			} else {

				rosesValidate.setExistRosal(false);
				rosesValidate.setMessage("Error: El rosal no esta registrado.");
			}
		} catch (Exception e) {

			rosesValidate.setExistRosal(false);
			rosesValidate.setMessage("Error: Imposiblie realizar la transaccion");
			logger.error("Error al realizar la operacion: " + e.getMessage());
		}

		return rosesValidate;
	}

	/**
	 * Metodo que reduce las existencias de un rosal.
	 */
	@Override
	public RosesValidate decrementaExistencia(Rosal rosal, RosesValidate rosesValidate) {

		try {
			int num = rosalRepository.countByIdRosal(rosal.getIdRosal());

			if (num > 0) {

				Rosal rosalActual = rosalRepository.findOne(rosal.getIdRosal());

				if (rosalActual.getCantidad() >= rosal.getCantidad()) {

					int existencias = rosalActual.getCantidad() - rosal.getCantidad();
					rosalActual.setCantidad(existencias);
					rosalRepository.save(rosalActual);
					rosesValidate.setExistRosal(true);
					rosesValidate.setMessage("Las existencias de " + rosalActual.getNombreRosal()
							+ " han sido reducidas en " + rosal.getCantidad() + " unidades correctamente.");
				} else {

					rosesValidate.setExistRosal(false);
					rosesValidate.setMessage("Error. La Existencia actual es inferior.");
				}

			} else {

				rosesValidate.setExistRosal(false);
				rosesValidate.setMessage("Error: El rosal no esta registrado.");
			}

		} catch (Exception e) {

			rosesValidate.setExistRosal(false);
			rosesValidate.setMessage("Error: Imposiblie realizar la transaccion");
			logger.error("Error al realizar la operacion: " + e.getMessage());

		}

		return rosesValidate;
	}

	/**
	 * Metodo que actualiza las existencias de un rosal.
	 */
	@Override
	public RosesValidate actualizaExistencia(Rosal rosal, RosesValidate rosesValidate) {

		try {
			int num = rosalRepository.countByIdRosal(rosal.getIdRosal());

			if (num > 0) {

				Rosal rosalActual = rosalRepository.findOne(rosal.getIdRosal());
				rosalActual.setCantidad(rosal.getCantidad());
				rosalRepository.save(rosalActual);
				rosesValidate.setExistRosal(true);
				rosesValidate.setMessage("Las existencias de " + rosalActual.getNombreRosal()
						+ " han sido actualizas a " + rosal.getCantidad() + " unidades.");

			} else {

				rosesValidate.setExistRosal(false);
				rosesValidate.setMessage("Error: El rosal no esta registrado.");
			}

		} catch (Exception e) {

			rosesValidate.setExistRosal(false);
			rosesValidate.setMessage("Error: Imposiblie realizar la transaccion");
			logger.error("Error al realizar la operacion: " + e.getMessage());
		}

		return rosesValidate;
	}

}
