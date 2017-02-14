package es.pdv.daw.proyect.services;

import es.pdv.daw.proyect.beans.AlbaranPdf;
import es.pdv.daw.proyect.beans.AlbaranValidate;
import es.pdv.daw.proyect.beans.ClientValidate;
import es.pdv.daw.proyect.entity.Albaran;
import es.pdv.daw.proyect.entity.Cliente;
import es.pdv.daw.proyect.entity.DetalleAlbaran;
import es.pdv.daw.proyect.entity.Rosal;
import es.pdv.daw.proyect.utilities.GeneratePdf;

/**
 * Interface de gestion de procesos de pedidos.
 * @author mint
 *
 */
public interface PedidosService {
	
	//Metodos documentados en la clases que implementan la interface.

	ClientValidate dameClientes(ClientValidate clientValidate);

	AlbaranValidate creaAlbaran(AlbaranValidate albaranValidate, Cliente cliente, Albaran albaran);

	AlbaranValidate insertaLineaDetalle(Rosal rosal, Albaran albaran,
			DetalleAlbaran detalleAlbaran, AlbaranValidate albaranValidate);

	AlbaranValidate eliminaLineaDetalle(Rosal rosal, Albaran albaran, DetalleAlbaran detalleAlbaran, AlbaranValidate albaranValidate);

	AlbaranValidate eliminaAlbaran(Albaran albaran, AlbaranValidate albaranValidate);

	AlbaranValidate damePedidos(AlbaranValidate albaranValidate);

	AlbaranValidate editaAlbaran(AlbaranValidate albaranValidate, Albaran albaran);

	AlbaranPdf generaPdf(AlbaranValidate albaranValidate, Albaran albaran, GeneratePdf generatePdf, AlbaranPdf albaranPdf);

}
