/**
 * 
 */
package org.sunnycake.aton.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 
 * POJO para la tabla SALA dentro de la base de datos. TODO: Getters y setters
 * 
 * @author Camilo Sampedro
 *
 */
@Entity
@Table(name = "SALA")
public class Sala {
	/**
	 * Clave primaria.
	 */
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="SALA_GEN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen")
	private int id;

	@Size(max = 32)
	@Column(name = "NOMBRE")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "LABORATORIO")
	private Laboratorio laboratorio;

	@Size()
	@Column(name = "MEDIOSAUDIOVISUALES")
	private String mediosAudiovisuales;

	@Column(name = "ENSERES")
	private String enseres;

	@OneToMany(targetEntity = Equipo.class, mappedBy = "sala", cascade = { CascadeType.ALL }, orphanRemoval = true)
	Set<Equipo> equipos = new HashSet<Equipo>();

	/*
	 * (non-Javadoc)
	 * 
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
	 * Verifica si dos salas son iguales a través de su ID
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Sala))
			return false;
		Sala other = (Sala) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * @return el id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            el/la id a ser asignado
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return el laboratorio
	 */
	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	/**
	 * @param laboratorio
	 *            el/la laboratorio a ser asignado
	 */
	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	/**
	 * @return el mediosAudiovisuales
	 */
	public String getMediosAudiovisuales() {
		return mediosAudiovisuales;
	}

	/**
	 * @param mediosAudiovisuales
	 *            el/la mediosAudiovisuales a ser asignado
	 */
	public void setMediosAudiovisuales(String mediosAudiovisuales) {
		this.mediosAudiovisuales = mediosAudiovisuales;
	}

	/**
	 * @return el enseres
	 */
	public String getEnseres() {
		return enseres;
	}

	/**
	 * @param enseres
	 *            el/la enseres a ser asignado
	 */
	public void setEnseres(String enseres) {
		this.enseres = enseres;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sala " + nombre + ", " + laboratorio;
	}

}
