/**
 * 
 */
package org.sunnycake.aton.dto;

import java.io.Serializable;

import org.joda.time.LocalDate;

/**
 * 
 * Clave primaria de la clase Estado
 * TODO: Getters y setters
 * @author Camilo Sampedro
 *
 */
public class EstadoPK implements Serializable {
	private static final long serialVersionUID = -1456864464154712540L;
	private Equipo pkEquipo;
	private LocalDate pkFecha;
	
	public EstadoPK() {
		// Constructor vacío
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pkEquipo == null) ? 0 : pkEquipo.hashCode());
		result = prime * result + ((pkFecha == null) ? 0 : pkFecha.hashCode());
		return result;
	}

	/**
	 * Verificar si esta clave primaria es igual a través de sus dos elementos
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EstadoPK))
			return false;
		EstadoPK other = (EstadoPK) obj;
		if (pkEquipo == null) {
			if (other.pkEquipo != null)
				return false;
		} else if (!pkEquipo.equals(other.pkEquipo))
			return false;
		if (pkFecha == null) {
			if (other.pkFecha != null)
				return false;
		} else if (!pkFecha.equals(other.pkFecha))
			return false;
		return true;
	}

	/**
	 * @return el pkEquipo
	 */
	public Equipo getPkEquipo() {
		return pkEquipo;
	}

	/**
	 * @param pkEquipo el/la pkEquipo a ser asignado
	 */
	public void setPkEquipo(Equipo pkEquipo) {
		this.pkEquipo = pkEquipo;
	}

	/**
	 * @return el pkFecha
	 */
	public LocalDate getPkFecha() {
		return pkFecha;
	}

	/**
	 * @param pkFecha el/la pkFecha a ser asignado
	 */
	public void setPkFecha(LocalDate pkFecha) {
		this.pkFecha = pkFecha;
	}

	/**
	 * @return el serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
