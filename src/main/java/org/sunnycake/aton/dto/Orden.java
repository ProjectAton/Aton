/**
 *
 */
package org.sunnycake.aton.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
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
public class Orden implements Serializable {

    /**
     * Clave primaria compuesta
     */
    @Id
    @ManyToOne(targetEntity = Equipo.class)
    @JoinColumn(name = "eq_sesion")
    private Equipo pkEquipo;

    @Id
    @DateTimeFormat(pattern = "dd/MM/yyyy - KK:mm:ss")
    @Type(type = "java.util.Date")
    @Column(name = "fe_sesion")
    private Date pkFecha;

    @ManyToOne
    @JoinColumn(name = "usuarioweb")
    private UsuarioWeb usuarioWeb;

    @Column(name = "COMANDO")
    private String comando;

    @Column(name = "RESULTADO")
    private String resultado;

    @Column(name = "CODIGOSALIDA")
    private int codigoSalida;

    @NotNull
    @Column(name = "INTERRUMPIR")
    private boolean interrumpir;

    @NotNull
    @Column(name = "SUDO")
    private boolean sudo;

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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) obj;
        if (pkEquipo == null) {
            if (other.pkEquipo != null) {
                return false;
            }
        } else if (!pkEquipo.equals(other.pkEquipo)) {
            return false;
        }
        if (pkFecha == null) {
            if (other.pkFecha != null) {
                return false;
            }
        } else if (!pkFecha.equals(other.pkFecha)) {
            return false;
        }
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
     * @return el orden
     */
    public String getComando() {
        return comando;
    }

    /**
     * @param comando el/la orden a ser asignado
     */
    public void setComando(String comando) {
        this.comando = comando;
    }

    /**
     * @return el resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * @param resultado el/la resultado a ser asignado
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
     * @param codigoSalida el/la codigoSalida a ser asignado
     */
    public void setCodigoSalida(int codigoSalida) {
        this.codigoSalida = codigoSalida;
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

    /**
     * @return el interrumpir
     */
    public boolean isInterrumpir() {
        return interrumpir;
    }

    /**
     * @param interrumpir el/la interrumpir a ser asignado
     */
    public void setInterrumpir(boolean interrumpir) {
        this.interrumpir = interrumpir;
    }

    /**
     * @return el sudo
     */
    public boolean isSudo() {
        return sudo;
    }

    /**
     * @param sudo el/la sudo a ser asignado
     */
    public void setSudo(boolean sudo) {
        this.sudo = sudo;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Orden [pkEquipo=");
        builder.append(pkEquipo);
        builder.append(", pkFecha=");
        builder.append(pkFecha);
        builder.append(", usuarioWeb=");
        builder.append(usuarioWeb);
        builder.append(", comando=");
        builder.append(comando);
        builder.append(", resultado=");
        builder.append(resultado);
        builder.append(", codigoSalida=");
        builder.append(codigoSalida);
        builder.append(", interrumpir=");
        builder.append(interrumpir);
        builder.append(", sudo=");
        builder.append(sudo);
        builder.append("]");
        return builder.toString();
    }

    public Orden(Equipo pkEquipo, Date pkFecha, UsuarioWeb usuarioWeb, String comando, String resultado, int codigoSalida, boolean interrumpir, boolean sudo) {
        this.pkEquipo = pkEquipo;
        this.pkFecha = pkFecha;
        this.usuarioWeb = usuarioWeb;
        this.comando = comando;
        this.resultado = resultado;
        this.codigoSalida = codigoSalida;
        this.interrumpir = interrumpir;
        this.sudo = sudo;
    }

    public Orden() {
    }

}
