package org.sunnycake.aton.dto;
// Generated 28/01/2016 05:23:03 PM by Hibernate Tools 4.3.1



/**
 * Sugerencia generated by hbm2java
 */
public class Sugerencia  implements java.io.Serializable {


     private long id;
     private Sesion sesion;
     private String sugerencia;

    public Sugerencia() {
    }

	
    public Sugerencia(long id) {
        this.id = id;
    }
    public Sugerencia(long id, Sesion sesion, String sugerencia) {
       this.id = id;
       this.sesion = sesion;
       this.sugerencia = sugerencia;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Sesion getSesion() {
        return this.sesion;
    }
    
    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }
    public String getSugerencia() {
        return this.sugerencia;
    }
    
    public void setSugerencia(String sugerencia) {
        this.sugerencia = sugerencia;
    }




}


