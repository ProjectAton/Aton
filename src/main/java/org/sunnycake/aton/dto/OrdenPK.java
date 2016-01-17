package org.sunnycake.aton.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Clave primaria de la clase Orden
 * 
 * @author camilo
 *
 */
public class OrdenPK implements Serializable {
	private static final long serialVersionUID = 2391560268127698216L;
	private Equipo pkEquipo;
	private Date pkFecha;

	public OrdenPK() {
		// Constructor vacío
	}

	/**
	 * @return el pkEquipo
	 */
	public Equipo getPkEquipo() {
		return pkEquipo;
	}

	/**
	 * @param pkEquipo
	 *            el/la pkEquipo a ser asignado
	 */
	public void setPkEquipo(Equipo pkEquipo) {
		this.pkEquipo = pkEquipo;
	}

	/**
	 * @return el pkFecha
	 */
	public Date getPkFecha() {
		return pkFecha;
	}

	/**
	 * @param pkFecha
	 *            el/la pkFecha a ser asignado
	 */
	public void setPkFecha(Date pkFecha) {
		this.pkFecha = pkFecha;
	}

	/**
	 * @return el serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OrdenPK))
			return false;
		OrdenPK other = (OrdenPK) obj;
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

}
