package es.pdv.daw.proyect.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa un cliente.
 * @author alumno
 *
 */
@Entity
@Table(name="clientes")
public class Cliente implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * identificador de cliente.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCliente;
	
	/**
	 * Nombre del cliente.
	 */
	@Column(name ="nombre_cliente", length=50, nullable=false, unique=true )
	private String nombreCliente;
	
	/**
	 * Email de cliente.
	 */
	@Column(name ="email", length=50, nullable=false)
	private String email;
	
	/**
	 * Tipo de identificador del cliente (DNI, NIF, CIF....).
	 */
	@Column(name ="tipo_identificador", length=20, nullable=false)
	private String tipoIden;
	
	/**
	 * Numero de identificador de cliente.
	 */
	@Column(name ="identificador", length=20, nullable=false, unique=true )
	private String Identificador;
	
	/**
	 * Direccion del cliente.
	 */
	@Column(name ="direccion", length=50)
	private String direccion;
	
	/**
	 * Codigo postal del cliente.
	 */
	@Column(name ="codigo_postal", length=5)
	private int cp;
	
	/**
	 * Municipio del cliente.
	 */
	@Column(name ="municipio", length=50, nullable=false)
	private String municipio;
	
	/**
	 * Provincia del cliente.
	 */
	@Column(name ="provincia", length=50, nullable=false)
	private String provincia;
	
	/**
	 * Pais del cliente.
	 */
	@Column(name ="pais", length=50, nullable=false)
	private String pais;
	
	/**
	 * Telefonos de contacto del cliente.
	 */
	@Column(name ="telefonos", length=50, nullable=false)
	private String telefonos;
	
	/**
	 * Observaciones sobre el cliente.
	 */
	@Column(name ="observaciones", length=200)
	private String observaciones;

	public Cliente() {
		super();
		
	}

	// Metodos setter y getter.
	
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoIden() {
		return tipoIden;
	}

	public void setTipoIden(String tipoIden) {
		this.tipoIden = tipoIden;
	}

	public String getIdentificador() {
		return Identificador;
	}

	public void setIdentificador(String identificador) {
		Identificador = identificador;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	
	
	

	
}
