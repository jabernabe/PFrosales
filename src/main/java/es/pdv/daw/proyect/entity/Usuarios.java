package es.pdv.daw.proyect.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad que representa la tabla usuarios de la base de datos.
 * @author jabd.
 *
 */
@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * identificador de usuario
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int idUsuario;
	
	/**
	 * Propiedad que encapsula el nombre de un usuario.
	 */
	@Column(name ="name", length=40, nullable=false )
	String name;
	
	/**
	 * Propiedad que encapsula los apellidos de un usuario.
	 */
	@Column(name ="surname", length=100, nullable=false)
	String surname;
	
	/**
	 * Propiedad que encapsula el email de un usuario.
	 */
	@Column(name ="email",length=80, nullable=false)
	String email;
	
	@Column(name ="login", length=40, nullable=false, unique=true)
	String login;
	
	/**
	 * Propiedad que encapula la clave de acceso del usuario.
	 */
	@Column(name ="password", length=40, nullable=false)
	String password;
	
	/**
	 * Propiedad que encapsula el rol de privilegios de un usuario.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="rol")
	private Roles rol;
	
	
	public Usuarios() {
		
	}

	public Usuarios(int idUsuario, String name, String surname, String login, String password, Roles rol) {
		super();
		this.idUsuario = idUsuario;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.rol = rol;
	}

	// Metodos setter y getter.
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Usuarios [idUsuario=" + idUsuario + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", login=" + login + ", password=" + password + ", rol=" + rol + "]";
	}
	

}
