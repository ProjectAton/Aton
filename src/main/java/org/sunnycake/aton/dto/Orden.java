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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * POJO para la tabla ORDEN en la base de datos TODO: Getters and Setters
 * 
 * @author Julian David Arango
 *
 */
@Entity
@Table(name = "ORDEN")
@IdClass(value = OrdenPK.class)
public class Orden {
	/**
	 * Clave primaria compuesta
	 */
	@Id
	@ManyToOne
	private Equipo pkEquipo;
	
	@Id
	@DateTimeFormat(pattern = "dd/MM/yyyy - KK:mm:ss")
	@Type(type = "java.util.Date")
	@Column(name = "FECHA")
	private Date pkFecha;

	@NotNull
	@ManyToOne
	private UsuarioWeb usuarioWeb;

	@Column(name = "ORDEN")
	private String orden;

	@Column(name = "RESULTADO")
	private String resultado;

	@Column(name = "CODIGOSALIDA")
	private int codigoSalida;

	@NotNull
	@ManyToOne
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
		if (!(obj instanceof Orden))
			return false;
		Orden other = (Orden) obj;
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
	 * @return el orden
	 */
	public String getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            el/la orden a ser asignado
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}

	/**
	 * @return el resultado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * @param resultado
	 *            el/la resultado a ser asignado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	/**
	 * @return el codigoSalida
	 */
	public int getCodigoSalida() {
		return codigoSalida;
	}

	/**
	 * @param codigoSalida
	 *            el/la codigoSalida a ser asignado
	 */
	public void setCodigoSalida(int codigoSalida) {
		this.codigoSalida = codigoSalida;
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
	 * @return el usuarioWeb
	 */
	public UsuarioWeb getUsuarioWeb() {
		return usuarioWeb;
	}

	/**
	 * @param usuarioWeb el/la usuarioWeb a ser asignado
	 */
	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
		this.usuarioWeb = usuarioWeb;
	}
	
	

}
