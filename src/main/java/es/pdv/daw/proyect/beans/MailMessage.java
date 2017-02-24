package es.pdv.daw.proyect.beans;

import org.springframework.stereotype.Component;

/**
 * Bean que representa un email de usuario.
 * 
 * @author jabd
 *
 */
@Component
public class MailMessage {

	/**
	 * Propiedad que encapsula el email de destino.
	 */
	private String to;

	/**
	 * Propiedad que encapsula el remitente del email.
	 */
	private String from;

	/**
	 * Propiedad que encapsula el asunto del email.
	 */
	private String subject;

	/**
	 * Propiedad que encapsula el cuerpo del email.
	 */
	private String body;

	/**
	 * Propiedad que encapsula el resultado del envio.
	 */
	private Boolean send;

	/**
	 * Propiedad que encapsula el mensaje a mostrar tras el envio del email.
	 */
	private String message;
	
	/**
	 * Propiedad que encapsula el mensaje de error de conexion.
	 */
	private boolean errorConexion;

	/**
	 * Constructor sin paramentros.
	 */
	public MailMessage() {

	}

	// Metodos getter y setter.

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Boolean getSend() {
		return send;
	}

	public void setSend(Boolean send) {
		this.send = send;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isErrorConexion() {
		return errorConexion;
	}

	public void setErrorConexion(boolean errorConexion) {
		this.errorConexion = errorConexion;
	}
	
	

}
