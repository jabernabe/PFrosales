package es.pdv.daw.proyect.entity;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa la tabla variedades de la bd.
 * @author alumno
 *
 */
@Entity
@Table(name="variedades")
public class Variedad implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador de variedad.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idVariedad;
	
	/**
	 * Nombre de la variedad.
	 */
	@Column(name ="nombre_variedad", length=50, nullable=false, unique=true )
	private String nombreVariedad;
	
	/**
	 * Distancia de plantacion de la variedad.
	 */
	@Column(name ="dist_plantacion", length=30, nullable=false )
	private String distPlantacion;
	
	/**
	 * Descripcion de la variedad.
	 */
	@Column(name ="descripcion", length=500, nullable=false )
	private String descripcion;
	
	
	
	/**
	 * Contructor por defecto.
	 */
	public Variedad() {
		super();
		
	}
	
	/**
	 * Constructor con parametros.
	 * @param idVariedad
	 * @param nombreVariedad
	 * @param distPlantacion
	 * @param descripcion
	 * @param rosales
	 */
	public Variedad(int idVariedad, String nombreVariedad, String distPlantacion, String descripcion,
			ArrayList<Rosal> rosales) {
		super();
		this.idVariedad = idVariedad;
		this.nombreVariedad = nombreVariedad;
		this.distPlantacion = distPlantacion;
		this.descripcion = descripcion;
		
	}

	// 	Metodos getter y setter.
	
	public int getIdVariedad() {
		return idVariedad;
	}

	public void setIdVariedad(int idVariedad) {
		this.idVariedad = idVariedad;
	}

	public String getNombreVariedad() {
		return nombreVariedad;
	}

	public void setNombreVariedad(String nombreVariedad) {
		this.nombreVariedad = nombreVariedad;
	}

	public String getDistPlantacion() {
		return distPlantacion;
	}

	public void setDistPlantacion(String distPlantacion) {
		this.distPlantacion = distPlantacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	


	
	
}
