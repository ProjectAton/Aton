/**
 * 
 */
package org.sunnycake.aton.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * POJO para la tabla Estado dentro de la base de datos. TODO: Getters y setters
 * 
 * @author Vanessa Pérez
 *
 */
@Entity
@Table(name = "ESTADO")
@IdClass(value=EstadoPK.class)
public class Estado implements Serializable {
	/**
	 * Clave primaria compuesta
	 */
	@Id
	@ManyToOne
        @JoinColumn(insertable = false, updatable = false)
	private Equipo pkEquipo;
	@Id
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name = "FECHA")
	private LocalDate pkFecha;

	@Column(name = "DESCRIPCION")
	private String descripcion;

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
	 * Verifica si dos estados son iguales a través de su equipo y su fecha
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Estado))
			return false;
		Estado other = (Estado) obj;
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
	 * @return el descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion el/la descripcion a ser asignado
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
