/**
 * 
 */
package org.sunnycake.aton.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * POJO para la tabla USUARIOWEB de la base de datos
 * TODO: Getters and Setters
 * @author Julian David Arango
 *
 */
@Entity
@Table(name = "USUARIOWEB")
public class UsuarioWeb {
	/**
	 * Clave primaria usuario
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String usuario;

	@Size(min = 6, max = 20)
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@ManyToOne
	private TipoUsuario tipoUsuario;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	/**
	 * Verifica si dos usuarios son iguales a través del nombre de usuario
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UsuarioWeb))
			return false;
		UsuarioWeb other = (UsuarioWeb) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
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
	 * @return el password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password el/la password a ser asignado
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return el tipoUsuario
	 */
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario el/la tipoUsuario a ser asignado
	 */
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
