package es.pdv.daw.proyect.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Clase que representa una empresa.
 * 
 * @author jabd
 *
 */
@Entity
@Table(name = "empresa")
public class Empresa {

	private static final long serialVersionUID = 1L;

	/**
	 * identificador de empresa.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEmpresa;

	/**
	 * Nombre del la empresa.
	 */
	@Column(name = "nombre_empresa", length = 50, nullable = false, unique = true)
	private String nombreEmpresa;

	/**
	 * Email de la empresa.
	 */
	@Column(name = "email", length = 50, nullable = false)
	private String email;

	/**
	 * Tipo de identificador de la empresa (DNI, NIF, CIF....).
	 */
	@Column(name = "tipo_identificador", length = 20, nullable = false)
	private String tipoIden;

	/**
	 * Numero de identificador de la empresa.
	 */
	@Column(name = "identificador", length = 20, nullable = false, unique = true)
	private String Identificador;

	/**
	 * Direccion de la empresa.
	 */
	@Column(name = "direccion", length = 50)
	private String direccion;

	/**
	 * Codigo postal de la empresa.
	 */
	@Column(name = "codigo_postal", length = 5)
	private String cp;

	/**
	 * Municipio de la empresa.
	 */
	@Column(name = "municipio", length = 50, nullable = false)
	private String municipio;

	/**
	 * Provincia de la empresa.
	 */
	@Column(name = "provincia", length = 50, nullable = false)
	private String provincia;

	/**
	 * Pais de la empresa.
	 */
	@Column(name = "pais", length = 50, nullable = false)
	private String pais;

	/**
	 * Telefonos de contacto del cliente.
	 */
	@Column(name = "telefonos", length = 50, nullable = false)
	private String telefonos;

	/**
	 * Observaciones sobre el cliente.
	 */
	@Column(name = "observaciones", length = 200)
	private String observaciones;

	/**
	 * Propiedad que encapsula la imagen del logo de la empresa.
	 */
	@Lob
	@Column(name = "imagen")
	private byte[] imagenLogo;

	/**
	 * Constructor sin parametros.
	 */
	public Empresa() {
		super();

	}

	// Getter y setter.

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
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


	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
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

	public byte[] getImagenLogo() {
		return imagenLogo;
	}

	public void setImagenLogo(byte[] imagenLogo) {
		this.imagenLogo = imagenLogo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}
	
	

}
