package org.sunnycake.aton.dto;
// Generated 28/01/2016 05:23:03 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Equipo generated by hbm2java
 */
public class Equipo  implements java.io.Serializable {


     private String ip;
     private Sala sala;
     private String mac;
     private String nombre;
     private String usuario;
     private String password;
     private String descripcion;
     private String mensaje;
     private Boolean seleccionado;

    public Equipo() {
    }

	
    public Equipo(String ip, String mac) {
        this.ip = ip;
        this.mac = mac;
    }
    public Equipo(String ip, Sala sala, String mac, String nombre, String usuario, String password, String descripcion, String mensaje, Boolean seleccionado, Set ordens, Set estados, Set sesions) {
       this.ip = ip;
       this.sala = sala;
       this.mac = mac;
       this.nombre = nombre;
       this.usuario = usuario;
       this.password = password;
       this.descripcion = descripcion;
       this.mensaje = mensaje;
       this.seleccionado = seleccionado;
    }
    
    public Equipo(String mac, String nombre, String usuario, String password, String ip, Sala sala, String descripcion, String mensaje, boolean seleccionado) {
        this.mac = mac;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.ip = ip;
        this.sala = sala;
        this.descripcion = descripcion;
        this.mensaje = mensaje;
        this.seleccionado = seleccionado;
    }
    
    public Equipo(String usuario, String password, String ip) {
        this.usuario = usuario;
        this.password = password;
        this.ip = ip;
    }
   
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    public Sala getSala() {
        return this.sala;
    }
    
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    public String getMac() {
        return this.mac;
    }
    
    public void setMac(String mac) {
        this.mac = mac;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getMensaje() {
        return this.mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public Boolean getSeleccionado() {
        return this.seleccionado;
    }
    
    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}


