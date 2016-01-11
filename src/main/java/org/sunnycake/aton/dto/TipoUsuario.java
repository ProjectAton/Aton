/**
 * 
 */
package org.sunnycake.aton.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO para la tabla TIPOUSUARIO en la base de datos.
 * TODO: Getters and Setters
 * @author Camilo Andrés Sampedro
 *
 */
@Entity
@Table(name = "TIPOUSUARIO")
public class TipoUsuario {
	/**
	 * Clave primaria
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name = "TIPO")
	private String tipo;

	/* (non-Javadoc)
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
	 * Verifica que dos tipos son iguales por el id
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TipoUsuario))
			return false;
		TipoUsuario other = (TipoUsuario) obj;
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
	 * @param id el/la id a ser asignado
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return el tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo el/la tipo a ser asignado
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
