package es.pdv.daw.proyect.services;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.pdv.daw.proyect.beans.AlbaranPdf;
import es.pdv.daw.proyect.beans.AlbaranValidate;
import es.pdv.daw.proyect.beans.ClientValidate;
import es.pdv.daw.proyect.dao.AlbaranesRepository;
import es.pdv.daw.proyect.dao.ClientesRepository;
import es.pdv.daw.proyect.dao.DetalleAlbaranRepository;
import es.pdv.daw.proyect.dao.EmpresaRepository;
import es.pdv.daw.proyect.dao.RosalRepository;
import es.pdv.daw.proyect.entity.Albaran;
import es.pdv.daw.proyect.entity.Cliente;
import es.pdv.daw.proyect.entity.DetalleAlbaran;
import es.pdv.daw.proyect.entity.Rosal;
import es.pdv.daw.proyect.utilities.GeneratePdf;

/**
 * Clase que gestiona los procesos de gestion de pedidos
 * 
 * @author jabd
 *
 */
@Service
public class PedidosServiceBean implements PedidosService {

	/**
	 * Propiedad que encapsula la instancia de Logger de la aplicacion.
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Propiedad que encapsula el objeto de acceso a datos tabla clientes.
	 */
	@Autowired
	ClientesRepository clientesRepository;

	/**
	 * Propiedad que encapsula el objeto de acceso a datos tabla albaranes.
	 */
	@Autowired
	AlbaranesRepository albaranesRepository;

	/**
	 * Propiedad que encapsula el objeto de acceso a datos tabla rosales.
	 */
	@Autowired
	RosalRepository rosalRepository;

	/**
	 * Propiedad que encapsula el objeto de acceso a datos tabla
	 * detalle_albaran.
	 */
	@Autowired
	DetalleAlbaranRepository detalleAlbaranlRepository;

	/**
	 * Propiedad que encapsula el objeto de acceso a datos tabla empresa.
	 */
	@Autowired
	private EmpresaRepository empresaRepository;

	/**
	 * Metodo que devuelve una lista de clientes.
	 */
	@Override
	public ClientValidate dameClientes(ClientValidate clientValidate) {

		try {
			List<Cliente> listaClientes = clientesRepository.findAllByOrderByNombreClienteAsc();

			if (listaClientes.isEmpty()) {

				clientValidate.setExistClientes(false);
				clientValidate.setMessage("No existen clientes registrados");
			} else {
				clientValidate.setExistClientes(true);
				clientValidate.setMessage("Clientes obtenidos correctamente");
				clientValidate.setListaClientes(listaClientes);
			}
		} catch (Exception e) {

			clientValidate.setExistClientes(false);
			clientValidate.setMessage("Error al realizar la transaccion.");
			logger.error("Error al obtener lista de clientes. La aplicación lanzó: " + e.getMessage());
		}
		return clientValidate;
	}

	/**
	 * Metodo que crea un nuevo albaran.
	 */
	@Override
	public AlbaranValidate creaAlbaran(AlbaranValidate albaranValidate, Cliente cliente, Albaran albaran) {

		try {
			// Se establece la fecha del sistema al pedido.
			Date fecha = new Date();
			albaran.setFecha(fecha);

			// Se añade el cliente al pedido.
			Cliente clienteCreado = clientesRepository.findByIdCliente(cliente.getIdCliente());
			Albaran albaranCreado = new Albaran();
			albaran.setCliente(clienteCreado);

			// Se graba el pedido en la base de datos.

			albaranCreado = albaranesRepository.saveAndFlush(albaran);

			// Se obtiene lista de rosales.

			List<Rosal> listaRosales = rosalRepository.findAllByOrderByNombreRosalAsc();

			// Se registran datos necesarios para el proceso de la peticion.
			albaranValidate.setMessage("albaran creado correctamente");
			albaranValidate.setExistAlbaran(true);
			albaranValidate.setAlbaran(albaranCreado);
			albaranValidate.setListaRosales(listaRosales);

			// Se obtienen la lista de detalle del pedido.
			List<DetalleAlbaran> lista = detalleAlbaranlRepository.findByAlbaran(albaran);
			albaranValidate.setListaDetalle(lista);

		} catch (Exception e) {

			albaranValidate.setMessage("Error al realizar la transacción.");
			albaranValidate.setExistAlbaran(false);
			logger.error("Error al crear el albarán. La aplicación lanzó: " + e.getMessage());
		}
		return albaranValidate;
	}

	/**
	 * Metodo que devuelve lista detalle de un pedido.
	 */
	@Override
	public AlbaranValidate insertaLineaDetalle(Rosal rosal, Albaran albaran, DetalleAlbaran detalleAlbaran,
			AlbaranValidate albaranValidate) {

		try {
			int stock = dameStock(rosal);

			if (stock >= detalleAlbaran.getCantidad()) {

				insertaLinea(rosal, albaran, detalleAlbaran);
				List<DetalleAlbaran> lista = detalleAlbaranlRepository.findByAlbaran(albaran);
				albaranValidate.setListaDetalle(lista);
				albaranValidate.setExistAlbaran(true);
				albaranValidate.setMessage("correcto");
			} else {
				albaranValidate.setExistAlbaran(false);
				albaranValidate.setMessage("Cantidad insuficiente en stock. Stock actual : " + stock + " rosales.");

			}

		} catch (Exception e) {

			albaranValidate.setMessage("Error al realizar la transacción.");
			albaranValidate.setExistAlbaran(false);
			logger.error("Error al insertar la linea en el albarán. La aplicación lanzó: " + e.getMessage());
		}
		return albaranValidate;
	}

	/**
	 * Metodo que devuelve la cantidad de rosales existentes.
	 * 
	 * @param rosal
	 * @return
	 */
	private int dameStock(Rosal rosal) {
		int stock = 0;

		try {
			Rosal rosa = rosalRepository.getOne(rosal.getIdRosal());
			stock = rosa.getCantidad();

		} catch (Exception e) {

			logger.error("Error al consultar el stock. La aplicación lanzó: " + e.getMessage());
		}
		return stock;
	}

	/**
	 * Metodo que inserta una linea en el detalle de un pedido.
	 * 
	 * @param rosal
	 * @param albaran
	 * @param detalleAlbaran
	 */
	private void insertaLinea(Rosal rosal, Albaran albaran, DetalleAlbaran detalleAlbaran) {

		try {
			Rosal rosa = rosalRepository.getOne(rosal.getIdRosal());

			int stockActualizado = rosa.getCantidad() - detalleAlbaran.getCantidad();
			rosa.setCantidad(stockActualizado);
			rosalRepository.save(rosa);

			Albaran albaran2 = albaranesRepository.getOne(albaran.getIdAlbaran());
			detalleAlbaran.setAlbaran(albaran2);
			detalleAlbaran.setRosal(rosa);
			detalleAlbaranlRepository.save(detalleAlbaran);

		} catch (Exception e) {
			logger.error("Error al insertar linea en albaran. La aplicación lanzó: " + e.getMessage());
		}
	}

	/**
	 * Metodo que elimina una linea del detalle del albaran.
	 */
	@Override
	public AlbaranValidate eliminaLineaDetalle(Rosal rosal, Albaran albaran, DetalleAlbaran detalleAlbaran,
			AlbaranValidate albaranValidate) {

		try {
			actualizaStock(rosal, detalleAlbaran);
			boolean detalleEliminado = eliminaLineaDetalle(detalleAlbaran);

			if (detalleEliminado) {

				List<DetalleAlbaran> lista = detalleAlbaranlRepository.findByAlbaran(albaran);
				albaranValidate.setListaDetalle(lista);
				albaranValidate.setExistAlbaran(true);
				albaranValidate.setMessage("correcto");
			} else {
				List<DetalleAlbaran> lista = detalleAlbaranlRepository.findByAlbaran(albaran);
				albaranValidate.setListaDetalle(lista);
				albaranValidate.setExistAlbaran(false);
				albaranValidate.setMessage("Error al eliminar la linea del albaran.");

			}

		} catch (Exception e) {

			albaranValidate.setExistAlbaran(false);
			albaranValidate.setMessage("Error al realizar la transacción");
			logger.error("Error al eliminar la linea de detalle del albaran. La aplicación lanzó: " + e.getMessage());
		}
		return albaranValidate;
	}

	/**
	 * Metodo que elimina una linea del detalle de un pedido.
	 * 
	 * @param detalleAlbaran
	 */
	private boolean eliminaLineaDetalle(DetalleAlbaran detalleAlbaran) {

		boolean eliminado = false;

		try {
			int numDetalle = detalleAlbaranlRepository.countByIdDetalleAlbaran(detalleAlbaran.getIdDetalleAlbaran());

			if (numDetalle > 0) {

				detalleAlbaranlRepository.delete(detalleAlbaran.getIdDetalleAlbaran());
				eliminado = true;
			}

		} catch (Exception e) {

			logger.error("Error al eliminar la linea de detalle del albaran. La aplicación lanzó: " + e.getMessage());
		}
		return eliminado;
	}

	/**
	 * Metodo que actualiza el stock de un rosal tras eliminar una linea del
	 * detalle de un pedido.
	 * 
	 * @param rosal
	 * @param detalleAlbaran
	 */
	private void actualizaStock(Rosal rosal, DetalleAlbaran detalleAlbaran) {

		try {
			int numRosal = rosalRepository.countByIdRosal(rosal.getIdRosal());

			if (numRosal > 0) {

				Rosal rosa = rosalRepository.getOne(rosal.getIdRosal());
				int nuevaCantidad = rosa.getCantidad() + detalleAlbaran.getCantidad();
				rosa.setCantidad(nuevaCantidad);
				rosalRepository.save(rosa);
			}
		} catch (Exception e) {

			logger.error("Error al actualizar el stock. La aplicación lanzó: " + e.getMessage());
		}
	}

	/**
	 * Metodo que elimina un albaran.
	 */
	@Override
	public AlbaranValidate eliminaAlbaran(Albaran albaran, AlbaranValidate albaranValidate) {

		try {
			actualizaStock(albaran);

			int numAlbaran = albaranesRepository.countByIdAlbaran(albaran.getIdAlbaran());

			if (numAlbaran > 0) {
				albaranesRepository.delete(albaran);
				albaranValidate.setExistAlbaran(true);
				albaranValidate.setMessage("Albaran eliminado correctamente");
			} else {
				albaranValidate.setExistAlbaran(false);
				albaranValidate.setMessage("Error: El albaran especificado no existe.");

			}

		} catch (Exception e) {

			albaranValidate.setExistAlbaran(false);
			albaranValidate.setMessage("Error al realizar la transacción");
			logger.error("Error al eliminar el albaran. La aplicación lanzó: " + e.getMessage());
		}
		return albaranValidate;
	}

	/**
	 * Metodo que actualiza el stock antes de elimanar un albaran.
	 * 
	 * @param albaran
	 */
	private void actualizaStock(Albaran albaran) {

		try {
			int numDetalle = detalleAlbaranlRepository.countByAlbaran(albaran);

			if (numDetalle > 0) {

				List<DetalleAlbaran> lista = detalleAlbaranlRepository.findByAlbaran(albaran);

				for (DetalleAlbaran detalle : lista) {

					Rosal rosa = detalle.getRosal();
					int stockActualizado = rosa.getCantidad() + detalle.getCantidad();
					rosa.setCantidad(stockActualizado);
				}
			}

		} catch (Exception e) {

			logger.error("Error al actualizar el stock. La aplicación lanzó: " + e.getMessage());
		}
	}

	/**
	 * Metodo que devuelve una lista de todos los pedidos.
	 */
	@Override
	public AlbaranValidate damePedidos(AlbaranValidate albaranValidate) {

		try {
			long numPedidos = albaranesRepository.count();

			if (numPedidos > 0) {

				List<Albaran> listaAlbaranes = albaranesRepository.findAll();
				albaranValidate.setListaAlbaranes(listaAlbaranes);
				albaranValidate.setExistAlbaran(true);
			}

		} catch (Exception e) {

			albaranValidate.setExistAlbaran(true);
			albaranValidate.setMessage("Error al realizar la transacción.");
			logger.error("Error obtener la lista de pedidos. La aplicación lanzó: " + e.getMessage());
		}
		return albaranValidate;
	}

	/**
	 * Metodo que edita un pedido.
	 */
	@Override
	public AlbaranValidate editaAlbaran(AlbaranValidate albaranValidate, Albaran albaran) {

		try {
			int numAlbaran = albaranesRepository.countByIdAlbaran(albaran.getIdAlbaran());

			if (numAlbaran > 0) {

				Albaran nuevoAlbaran = albaranesRepository.findByIdAlbaran(albaran.getIdAlbaran());

				// Se obtiene lista de rosales.
				List<Rosal> listaRosales = rosalRepository.findAllByOrderByNombreRosalAsc();

				// Se registran datos necesarios para el proceso de la peticion.
				albaranValidate.setMessage("albaran creado correctamente");
				albaranValidate.setExistAlbaran(true);
				albaranValidate.setAlbaran(nuevoAlbaran);
				albaranValidate.setListaRosales(listaRosales);

				// Se obtienen la lista de detalle del pedido.
				List<DetalleAlbaran> lista = detalleAlbaranlRepository.findByAlbaran(nuevoAlbaran);
				albaranValidate.setListaDetalle(lista);

			}

		} catch (Exception e) {

			albaranValidate.setMessage("Error al realizar la transacción");
			albaranValidate.setExistAlbaran(false);
			logger.error("Error al editar el albarán. La aplicación lanzó: " + e.getMessage());
		}

		return albaranValidate;
	}

	/**
	 * Metodo que genera un archivo pdf de un pedido.
	 */
	@Override
	public AlbaranPdf generaPdf(AlbaranValidate albaranValidate, Albaran albaran, GeneratePdf generatePdf,
			AlbaranPdf albaranPdf) {

		try {
			int numAlbaran = albaranesRepository.countByIdAlbaran(albaran.getIdAlbaran());

			if (numAlbaran > 0) {

				Albaran nuevoAlbaran = albaranesRepository.findByIdAlbaran(albaran.getIdAlbaran());

				// Se registran datos necesarios para el proceso de la peticion.
				albaranValidate.setMessage("archivo pdf creado correctamente");
				albaranValidate.setExistAlbaran(true);
				albaranValidate.setAlbaran(nuevoAlbaran);

				// Se obtienen la lista de detalle del pedido.
				List<DetalleAlbaran> lista = detalleAlbaranlRepository.findByAlbaran(nuevoAlbaran);
				albaranValidate.setListaDetalle(lista);

				albaranPdf = generatePdf.generaPdf(albaranValidate, albaranPdf, empresaRepository);
			}
			
		} catch (Exception e) {

			albaranValidate.setMessage("Error al realizar la transacción");
			albaranValidate.setExistAlbaran(false);
			logger.error("Error al generar pdf. La aplicación lanzó: " + e.getMessage());

		}

		return albaranPdf;
	}
}
