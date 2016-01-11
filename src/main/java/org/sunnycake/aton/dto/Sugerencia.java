/**
 * 
 */
package org.sunnycake.aton.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Camilo Sampedro
 *
 */
@Entity
@Table(name = "SUGERENCIA")
public class Sugerencia {
	/**
	 * Clave primaria
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "SUGERENCIA")
	private String sugerencia;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "EQ_SESION"), @JoinColumn(name="FE_SESION") })
	private Sesion sesion;

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
	 * Verifica si dos sugerencias son iguales por el id
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Sugerencia))
			return false;
		Sugerencia other = (Sugerencia) obj;
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
	 * @param id
	 *            el/la id a ser asignado
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return el sugerencia
	 */
	public String getSugerencia() {
		return sugerencia;
	}

	/**
	 * @param sugerencia
	 *            el/la sugerencia a ser asignado
	 */
	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}

	/**
	 * @return el sesion
	 */
	public Sesion getSesion() {
		return sesion;
	}

	/**
	 * @param sesion
	 *            el/la sesion a ser asignado
	 */
	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

}
