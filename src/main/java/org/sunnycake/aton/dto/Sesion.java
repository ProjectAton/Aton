/**
 * 
 */
package org.sunnycake.aton.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * POJO para la tabla SESION en la base de datos TODO: Getters y setters
 * 
 * @author Camilo Sampedro
 *
 */
@Entity
@Table(name = "SESION")
@IdClass(value = SesionPK.class)
public class Sesion {
	/**
	 * Clave primaria compuesta
	 */
	@Id
	@DateTimeFormat(pattern = "dd/MM/yyyy - KK:mm:ss")
	@Type(type = "java.util.Date")
	@Column(name = "FECHA")
	private Date pkFecha;
	
	@Id
	@ManyToOne
	private Equipo pkEquipo;
	
	@Column(name = "USUARIO")
	private String usuario;

	@Column(name = "SISTEMAOPERATIVO")
	private String sistemaOperativo;
	
	
	
	@Column(name = "ACTIVA")
	private boolean activa;

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
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		if (!(obj instanceof Sesion))
			return false;
		Sesion other = (Sesion) obj;
		if (pkFecha == null) {
			if (other.pkFecha != null)
				return false;
		} else if (!pkFecha.equals(other.pkFecha))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
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
	 * @return el sistemaOperativo
	 */
	public String getSistemaOperativo() {
		return sistemaOperativo;
	}

	/**
	 * @param sistemaOperativo el/la sistemaOperativo a ser asignado
	 */
	public void setSistemaOperativo(String sistemaOperativo) {
		this.sistemaOperativo = sistemaOperativo;
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
	 * @return el usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario el/la usuario a ser asignado
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return el activa
	 */
	public boolean isActiva() {
		return activa;
	}

	/**
	 * @param activa el/la activa a ser asignado
	 */
	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	

}
