package es.pdv.daw.proyect.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import es.pdv.daw.proyect.beans.MailMessage;

/**
 * Clase que procesa los envios de email.
 * @author jabd
 *
 */
@Component
public class NotificationMail {
	
	/**
	 * Propiedad que encapsula la instacia de javaMail para envio de email.
	 */
	private JavaMailSender javaMailSender;
	
	
	@Autowired
	public NotificationMail(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	/**
	 * Metodo que envia los email.
	 * @param message
	 * @throws MailException
	 */
	
	public void send(MailMessage message) throws MailException {
		
		SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(message.getTo());
        mail.setFrom(message.getFrom());
        mail.setSubject(message.getSubject());
        mail.setText(message.getBody());
		
		javaMailSender.send(mail);
	}
	
	// Getter y setter.
	
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	

}
