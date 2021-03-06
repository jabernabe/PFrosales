package es.pdv.daw.proyect.beans;

import java.util.List;
import org.springframework.stereotype.Component;
import es.pdv.daw.proyect.entity.Albaran;
import es.pdv.daw.proyect.entity.DetalleAlbaran;
import es.pdv.daw.proyect.entity.Rosal;

/**
 * Clase de proceso de datos de peticiones ajax del menu pedidos.
 * 
 * @author jabd
 *
 */
@Component
public class AlbaranValidate {

	/**
	 * Propiedad que encapsula el mensaje tras la transaccion.
	 */
	private String message;

	/**
	 * Propiedad que encapsula el resutado de la transaccion.
	 */
	private boolean existAlbaran;

	/**
	 * Propiedad que encapsula un albaran
	 */
	private Albaran albaran;

	/**
	 * Propiedad que encapsula la lista de rosales registrados.
	 */
	private List<Rosal> listaRosales;

	/**
	 * Propiedad que encapsula lista detalle de un albaran.
	 */
	private List<DetalleAlbaran> listaDetalle;

	/**
	 * Propiedad que encapusla la lista de albaranes registrados.
	 */
	private List<Albaran> listaAlbaranes;

	/**
	 * Constructor sin parametros.
	 */
	public AlbaranValidate() {
		super();
	}

	// Metodos setter y getter.

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isExistAlbaran() {
		return existAlbaran;
	}

	public void setExistAlbaran(boolean existAlbaran) {
		this.existAlbaran = existAlbaran;
	}

	public Albaran getAlbaran() {
		return albaran;
	}

	public void setAlbaran(Albaran albaran) {
		this.albaran = albaran;
	}

	public List<DetalleAlbaran> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(List<DetalleAlbaran> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

	public List<Rosal> getListaRosales() {
		return listaRosales;
	}

	public void setListaRosales(List<Rosal> listaRosales) {
		this.listaRosales = listaRosales;
	}

	public List<Albaran> getListaAlbaranes() {
		return listaAlbaranes;
	}

	public void setListaAlbaranes(List<Albaran> listaAlbaranes) {
		this.listaAlbaranes = listaAlbaranes;
	}

}
