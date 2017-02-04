package es.pdv.daw.proyect.beans;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase de proceso de albaranes.
 * @author jabd
 *
 */
@Component
public class AlbaranPdf {
	
	/**
	 * Propiedad que encapsula el resultado de la creacion del archivo.
	 */
	private boolean isCreated;
	
	/**
	 * Propiedad que encapsula el mensage tras procesar el archivo.
	 */
	private String message;
	
	/**
	 * Propiedad que encapsula el nombre del archivo generado.
	 */
	private String pdfName;
	
	/**
	 * Propiedad que encapsula el archivo codificado en base 64.
	 */
	@JsonProperty
    private String filePdf;
	
	/**
	 * Contructor.
	 */
	public AlbaranPdf() {
		super();
		
	}

	// Metodos setter y getter.
	
	public boolean isCreated() {
		return isCreated;
	}

	public void setCreated(boolean isCreated) {
		this.isCreated = isCreated;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public String getFilePdf() {
		return filePdf;
	}

	public void setFilePdf(String filePdf) {
		this.filePdf = filePdf;
	}
	
}
