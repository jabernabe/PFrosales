package es.pdv.daw.proyect.services;

import java.util.List;

import es.pdv.daw.proyect.beans.EmpresaValidate;
import es.pdv.daw.proyect.beans.MailMessage;
import es.pdv.daw.proyect.beans.UserValidate;
import es.pdv.daw.proyect.entity.Usuarios;

/**
 * Interface de servicios de login y administracion de usuarios.
 * @author jabd
 *
 */
public interface LoginService {
	
	

	UserValidate validaUsuario(String login, String password, UserValidate userValidate);
	
	List<Usuarios> findAllUsers();

	Usuarios dameUno(int id);

	UserValidate updateUser(Usuarios usuario);

	UserValidate insertUser(Usuarios usuario);

	UserValidate deleteUser(Usuarios usuario);

	MailMessage sendMail(MailMessage message);		

}
