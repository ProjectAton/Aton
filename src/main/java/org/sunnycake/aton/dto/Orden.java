package org.sunnycake.aton.dto;
// Generated 28/01/2016 05:23:03 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Orden generated by hbm2java
 */
public class Orden  implements java.io.Serializable {


     private OrdenPK id;
     private Equipo equipo;
     private UsuarioWeb usuarioweb;
     private Integer id_1;
     private Date feSesion;
     private Boolean interrumpir;
     private Boolean sudo;
     private String comando;
     private String resultado;
     private Integer codigosalida;

    public Orden() {
    }

	
    public Orden(OrdenPK id, Equipo equipo, UsuarioWeb usuarioweb) {
        this.id = id;
        this.equipo = equipo;
        this.usuarioweb = usuarioweb;
    }
    public Orden(OrdenPK id, Equipo equipo, UsuarioWeb usuarioweb, Integer id_1, Date feSesion, Boolean interrumpir, Boolean sudo, String comando, String resultado, Integer codigosalida) {
       this.id = id;
       this.equipo = equipo;
       this.usuarioweb = usuarioweb;
       this.id_1 = id_1;
       this.feSesion = feSesion;
       this.interrumpir = interrumpir;
       this.sudo = sudo;
       this.comando = comando;
       this.resultado = resultado;
       this.codigosalida = codigosalida;
    }
   
    public OrdenPK getId() {
        return this.id;
    }
    
    public void setId(OrdenPK id) {
        this.id = id;
    }
    public Equipo getEquipo() {
        return this.equipo;
    }
    
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    public UsuarioWeb getUsuarioweb() {
        return this.usuarioweb;
    }
    
    public void setUsuarioweb(UsuarioWeb usuarioweb) {
        this.usuarioweb = usuarioweb;
    }
    public Integer getId_1() {
        return this.id_1;
    }
    
    public void setId_1(Integer id_1) {
        this.id_1 = id_1;
    }
    public Date getFeSesion() {
        return this.feSesion;
    }
    
    public void setFeSesion(Date feSesion) {
        this.feSesion = feSesion;
    }
    public Boolean getInterrumpir() {
        return this.interrumpir;
    }
    
    public void setInterrumpir(Boolean interrumpir) {
        this.interrumpir = interrumpir;
    }
    public Boolean getSudo() {
        return this.sudo;
    }
    
    public void setSudo(Boolean sudo) {
        this.sudo = sudo;
    }
    public String getComando() {
        return this.comando;
    }
    
    public void setComando(String comando) {
        this.comando = comando;
    }
    public String getResultado() {
        return this.resultado;
    }
    
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public Integer getCodigosalida() {
        return this.codigosalida;
    }
    
    public void setCodigosalida(Integer codigosalida) {
        this.codigosalida = codigosalida;
    }
    public Orden(Equipo pkEquipo, Date pkFecha, UsuarioWeb usuarioWeb, String comando, String resultado, int codigoSalida, boolean interrumpir, boolean sudo) {
        this.id = new OrdenPK(equipo.getIp(), pkFecha);
        this.equipo = pkEquipo;
        this.feSesion = pkFecha;
        this.usuarioweb = usuarioWeb;
        this.comando = comando;
        this.resultado = resultado;
        this.codigosalida = codigoSalida;
        this.interrumpir = interrumpir;
        this.sudo = sudo;
    }

}


