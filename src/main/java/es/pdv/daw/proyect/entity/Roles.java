package es.pdv.daw.proyect.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad que representa la tabla roles de la base de datos.
 * 
 * @author jabd
 *
 */
@Entity
@Table(name = "roles")
public class Roles implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identificador de rol.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRol;

	/**
	 * nombre del rol.
	 */
	@Column(name = "rol_name", length = 40, nullable = false, unique = true)
	private String rolName;

	/**
	 * Propiedad que encapsula la lista de usuarios.
	 */
	@OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Usuarios> usuarios = new ArrayList<>();

	/**
	 * Constructor sin parametros.
	 */
	public Roles() {

	}

	/**
	 * Constructor con parametros.
	 * 
	 * @param idRol
	 * @param rolName
	 * @param usuarios
	 */
	public Roles(int idRol, String rolName, List<Usuarios> usuarios) {
		super();
		this.idRol = idRol;
		this.rolName = rolName;
		this.usuarios = usuarios;
	}

	// METODOS SETTER Y GETTER.

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

}
