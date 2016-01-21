/**
 * 
 */
package org.sunnycake.aton.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * POJO para la tabla Equipo dentro de la base de datos
 * 
 * @author Camilo Sampedro
 */
@Entity
@Table(name = "EQUIPO")
public class Equipo implements Serializable {
	/**
	 * Clave primaria.
	 */
	@Size(min = 17, max = 17)
	@Column(name = "MAC")
	private String mac;

	@Size(max = 15)
	@Column(name = "NOMBRE")
	private String nombre;

	@Size(max = 32)
	@NotNull
	@Column(name = "USUARIO")
	private String usuario;

	@NotNull
	@Column(name = "PASSWORD")
	private String password;

	/**
	 * Ip conformada por hileras del tipo X.X.X.X regexp es la expresión regular
	 * para las IP permitidas
	 */
	@Id
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "IP", unique = true)
	private String ip;

	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "SALA")
	private Sala sala;

	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "MENSAJE")
	private String mensaje;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Orden.class, mappedBy = "pkEquipo", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Set<Orden> ordenes = new HashSet<>();
	
	@Column(name = "SELECCIONADO")
	private boolean seleccionado;

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

	/**
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            el/la nombre a ser asignado
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return el usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            el/la usuario a ser asignado
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return el password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            el/la password a ser asignado
	 */
	public void setPassword(String password) {
		this.password = password;
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

	/**
	 * @return el ordenes
	 */
	public Set<Orden> getOrdenes() {
		return ordenes;
	}

	/**
	 * @param ordenes
	 *            el/la ordenes a ser asignado
	 */
	public void setOrdenes(Set<Orden> ordenes) {
		this.ordenes = ordenes;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	/**
	 * @return el mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje el/la mensaje a ser asignado
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
