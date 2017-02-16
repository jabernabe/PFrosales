package es.pdv.daw.proyect.beans;

import java.util.List;

import org.springframework.stereotype.Component;

import es.pdv.daw.proyect.entity.Usuarios;

/**
 * Bean de proceso de datos para el logueo de usuarios
 * 
 * @author jabd
 *
 */
@Component
public class UserValidate {

	/**
	 * Usuario que inicia sesion.
	 */
	private Usuarios usuario;

	/**
	 * Mensaje de retorno en caso de logueo erroneo.
	 */
	String message;

	/**
	 * Propiedad que evalua la existencia del usuario en base de datos.
	 */
	Boolean userExist;
	
	/**
	 * Propiedad que encapsula en mensaje en caso de error de conexion a base de datos.
	 */
	boolean errorConexion;
	
	private List<Usuarios> listaUsuarios;

	/**
	 * Constructor sin parametros.
	 */
	public UserValidate() {
	}

	// Metodos setter y getter

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getUserExist() {
		return userExist;
	}

	public void setUserExist(Boolean userExist) {
		this.userExist = userExist;
	}

	public boolean isErrorConexion() {
		return errorConexion;
	}

	public void setErrorConexion(boolean errorConexion) {
		this.errorConexion = errorConexion;
	}

	public List<Usuarios> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuarios> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	
}
