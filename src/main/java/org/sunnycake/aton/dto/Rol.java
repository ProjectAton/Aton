/**
 *
 */
package org.sunnycake.aton.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * POJO para la tabla TIPOUSUARIO en la base de datos. TODO: Getters and Setters
 *
 * @author Camilo Andrés Sampedro
 *
 */
@Entity
@Table(name = "ROL")
public class Rol {

    /**
     * Clave primaria
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIOWEB")
    private UsuarioWeb usuarioWeb;

    @Column(name = "ROL")
    private String rol;

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
     * @return el rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol el/la rol a ser asignado
     */
    public void setRol(String rol) {
        this.rol = rol;
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
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) obj;
        if (id != other.id) {
            return false;
        }
        return true;
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

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Rol [id=");
        builder.append(id);
        builder.append(", usuarioWeb=");
        builder.append(usuarioWeb);
        builder.append(", rol=");
        builder.append(rol);
        builder.append("]");
        return builder.toString();
    }

    public Rol(long id, UsuarioWeb usuarioWeb, String rol) {
        this.id = id;
        this.usuarioWeb = usuarioWeb;
        this.rol = rol;
    }

    public Rol() {
    }

}
