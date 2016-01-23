/**
 * 
 */
package org.sunnycake.aton.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Contenedor de salas. POJO de la tabla laboratorio. TODO: Getters y setters
 * 
 * @author Camilo Sampedro
 *
 */
@Entity
@Table(name = "LABORATORIO")
public class Laboratorio implements Serializable {
	/**
	 * Clave primaria.
	 */
	@Id
	@SequenceGenerator(name="lab-gen",sequenceName="LAB_GEN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lab-gen")
	private long id;

	@Column(name = "UBICACION")
	private String ubicacion;

	@Column(name = "ADMINISTRACION")
	private String administracion;
	
	@Size(max=32)
	@Column(name="NOMBRE")
	private String nombre;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/**
	 * Verifica si dos laboratorios son iguales a través de su ID
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Laboratorio))
			return false;
		Laboratorio other = (Laboratorio) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * @return el id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id el/la id a ser asignado
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return el ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion el/la ubicacion a ser asignado
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return el administracion
	 */
	public String getAdministracion() {
		return administracion;
	}

	/**
	 * @param administracion el/la administracion a ser asignado
	 */
	public void setAdministracion(String administracion) {
		this.administracion = administracion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Laboratorio " + nombre;
	}

	/**
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre el/la nombre a ser asignado
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    public Laboratorio(long id, String ubicacion, String administracion, String nombre) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.administracion = administracion;
        this.nombre = nombre;
    }

    public Laboratorio() {
    }
    
    
	
	
	

}
