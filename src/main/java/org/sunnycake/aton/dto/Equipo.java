/**
 * 
 */
package org.sunnycake.aton.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * POJO para la tabla Equipo dentro de la base de datos. TODO: Getters y setters
 * 
 * @author Camilo Sampedro
 */
@Entity
@Table(name = "EQUIPO")
public class Equipo {
	/**
	 * Clave primaria.
	 */
	@Id
	@Size(min = 17, max = 17)
	private String mac;

	/**
	 * Ip conformada por hileras del tipo X.X.X.X regexp es la expresión regular
	 * para las IP permitidas
	 */
	@Pattern(regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
	@Column(name = "IP", unique = true)
	private String ip;

	@ManyToOne
	@JoinColumn(name = "SALA")
	private Sala sala;

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
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		return result;
	}

	/**
	 * Verifica que dos equipos son iguales a través de la MAC
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Equipo))
			return false;
		Equipo other = (Equipo) obj;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		return true;
	}

	/**
	 * @return el mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * @param mac
	 *            el/la mac a ser asignado
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * @return el ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            el/la ip a ser asignado
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return el sala
	 */
	public Sala getSala() {
		return sala;
	}

	/**
	 * @param sala
	 *            el/la sala a ser asignado
	 */
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	/**
	 * @return el descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            el/la descripcion a ser asignado
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Equipo [mac=");
		builder.append(mac);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", sala=");
		builder.append(sala);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append("]");
		return builder.toString();
	}

}
