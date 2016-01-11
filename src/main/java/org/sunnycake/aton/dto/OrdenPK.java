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
	private UsuarioWeb pkUsuario;
	private Date pkFecha;
	
	public OrdenPK() {
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
		result = prime * result + ((pkFecha == null) ? 0 : pkFecha.hashCode());
		result = prime * result + ((pkUsuario == null) ? 0 : pkUsuario.hashCode());
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
		if (pkFecha == null) {
			if (other.pkFecha != null)
				return false;
		} else if (!pkFecha.equals(other.pkFecha))
			return false;
		if (pkUsuario == null) {
			if (other.pkUsuario != null)
				return false;
		} else if (!pkUsuario.equals(other.pkUsuario))
			return false;
		return true;
	}

	/**
	 * @return el pkUsuario
	 */
	public UsuarioWeb getPkUsuario() {
		return pkUsuario;
	}

	/**
	 * @param pkUsuario el/la pkUsuario a ser asignado
	 */
	public void setPkUsuario(UsuarioWeb pkUsuario) {
		this.pkUsuario = pkUsuario;
	}

	/**
	 * @return el pkFecha
	 */
	public Date getPkFecha() {
		return pkFecha;
	}

	/**
	 * @param pkFecha el/la pkFecha a ser asignado
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

}
