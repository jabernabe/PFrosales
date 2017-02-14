package es.pdv.daw.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.pdv.daw.proyect.beans.AlbaranPdf;
import es.pdv.daw.proyect.beans.AlbaranValidate;
import es.pdv.daw.proyect.beans.ClientValidate;
import es.pdv.daw.proyect.entity.Albaran;
import es.pdv.daw.proyect.entity.Cliente;
import es.pdv.daw.proyect.entity.DetalleAlbaran;
import es.pdv.daw.proyect.entity.Rosal;
import es.pdv.daw.proyect.services.PedidosService;
import es.pdv.daw.proyect.utilities.GeneratePdf;

/**
 * Clase controladora que procesa las peticiones ajax del menu pedidos.
 * 
 * @author jabd
 *
 */
@Controller
@ResponseBody
public class PedidosController {

	/**
	 * Propiedad que encapsula el objeto de acceso a base de datos .
	 */
	@Autowired
	private PedidosService pedidosService;

	/**
	 * Metodo que devuelve una lista de clientes.
	 * 
	 * @param clientValidate
	 * @return
	 */
	@RequestMapping(value = "dameClientes", method = RequestMethod.GET)
	public ClientValidate dameClientes(ClientValidate clientValidate) {

		return pedidosService.dameClientes(clientValidate);
	}

	/**
	 * Metodo que crea un albaran.
	 * 
	 * @param albaranValidate
	 * @param cliente
	 * @return
	 */
	@RequestMapping(value = "creaAlbaran", method = RequestMethod.POST)
	public AlbaranValidate creaAlbaran(AlbaranValidate albaranValidate, Cliente cliente, Albaran albaran) {

		return pedidosService.creaAlbaran(albaranValidate, cliente, albaran);
	}

	/**
	 * Metodo que inserta una linea en el detalle de un pedido.
	 * 
	 * @param albaranValidate
	 * @param rosal
	 * @param albaran
	 * @param detalleAlbaran
	 * @return
	 */
	@RequestMapping(value = "insertaLinea", method = RequestMethod.POST)
	public AlbaranValidate insertaLinea(AlbaranValidate albaranValidate, Rosal rosal, Albaran albaran,
			DetalleAlbaran detalleAlbaran) {

		return pedidosService.insertaLineaDetalle(rosal, albaran, detalleAlbaran, albaranValidate);
	}

	/**
	 * Metodo que elimina una linea del albaran.
	 * 
	 * @param albaranValidate
	 * @param rosal
	 * @param albaran
	 * @param detalleAlbaran
	 * @return
	 */
	@RequestMapping(value = "eliminaLinea", method = RequestMethod.POST)
	public AlbaranValidate eliminaLinea(AlbaranValidate albaranValidate, Rosal rosal, Albaran albaran,
			DetalleAlbaran detalleAlbaran) {

		return pedidosService.eliminaLineaDetalle(rosal, albaran, detalleAlbaran, albaranValidate);
	}

	/**
	 * Metodo que elimina un albaran.
	 * 
	 * @param albaranValidate
	 * @param albaran
	 * @return
	 */
	@RequestMapping(value = "eliminaAlbaran", method = RequestMethod.POST)
	public AlbaranValidate eliminaAlbaran(AlbaranValidate albaranValidate, Albaran albaran) {

		return pedidosService.eliminaAlbaran(albaran, albaranValidate);
	}

	/**
	 * Metodo que devuelve una lista de pedidos.
	 * 
	 * @param albaranValidate
	 * @return
	 */
	@RequestMapping(value = "damePedidos", method = RequestMethod.GET)
	public AlbaranValidate damePedidos(AlbaranValidate albaranValidate) {

		return pedidosService.damePedidos(albaranValidate);
	}

	/**
	 * Metodo que edita un albaran
	 * 
	 * @param albaranValidate
	 * @param albaran
	 * @return
	 */
	@RequestMapping(value = "editaAlbaran", method = RequestMethod.POST)
	public AlbaranValidate editaAlbaran(AlbaranValidate albaranValidate, Albaran albaran) {

		return pedidosService.editaAlbaran(albaranValidate, albaran);
	}

	/**
	 * Metodo que genera archivo pdf de un pedido.
	 * 
	 * @param albaranValidate
	 * @param albaran
	 * @return
	 */
	@RequestMapping(value = "generaPdf", method = RequestMethod.POST)
	public AlbaranPdf generaPdf(AlbaranValidate albaranValidate, Albaran albaran, AlbaranPdf albaranPdf,
			GeneratePdf generatePdf) {

		return pedidosService.generaPdf(albaranValidate, albaran, generatePdf, albaranPdf);
	}

}
