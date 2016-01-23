/**
 *
 */
package org.sunnycake.aton.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * POJO para la tabla USUARIOWEB de la base de datos TODO: Getters and Setters
 *
 * @author Julian David Arango
 *
 */
/**
 * @author camilo
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

    @Column(name = "ENABLED")
    private boolean enabled;

    @OneToMany(targetEntity = Rol.class, mappedBy = "usuarioWeb", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Rol> rolesDeUsuario = new HashSet<Rol>(0);

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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UsuarioWeb)) {
            return false;
        }
        UsuarioWeb other = (UsuarioWeb) obj;
        if (usuario == null) {
            if (other.usuario != null) {
                return false;
            }
        } else if (!usuario.equals(other.usuario)) {
            return false;
        }
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
     * @return el enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled el/la enabled a ser asignado
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Rol> getRolesDeUsuario() {
        return this.rolesDeUsuario;
    }

    public void setRolesDeUsuario(Set<Rol> rolesDeUsuario) {
        this.rolesDeUsuario = rolesDeUsuario;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UsuarioWeb [usuario=");
        builder.append(usuario);
        builder.append(", password=");
        builder.append(password);
        builder.append(", enabled=");
        builder.append(enabled);
        builder.append("]");
        return builder.toString();
    }

    public UsuarioWeb() {
    }

    public UsuarioWeb(String usuario, String password, boolean enabled) {
        this.usuario = usuario;
        this.password = password;
        this.enabled = enabled;
    }

}
