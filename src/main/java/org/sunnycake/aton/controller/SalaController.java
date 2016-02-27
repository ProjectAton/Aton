/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sunnycake.aton.controller;

import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Laboratorio;
import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.service.EquipoService;
import org.sunnycake.aton.service.LaboratorioService;
import org.sunnycake.aton.service.SalaService;

/**
 * Controlador de Sala
 *
 * @author Julian Arango
 */
@Component
@Path("salas")
public class SalaController {

    private final Logger logger = LogManager.getLogger(EquipoController.class.getName());
    @Autowired
    SalaService salaService;
    @Autowired
    LaboratorioService laboratorioService;
    @Autowired
    EquipoService equipoService;
    Sala sala;
    Laboratorio lab;

    /**
     * Metodo para almacenar una sala
     *
     * @param id id de la sala
     * @param nombre Nombre de la sala
     * @param enseres Enseres que posee la sala
     * @param mediosAudio Que medios audiovisuales posee
     * @param laboratory Laboratorio al que pertenece
     * @return un mensaje en txt con el resultado de la insercion o una entrada
     * en el log
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    public String crear(@QueryParam("id") int id, @QueryParam("nombre") String nombre, @QueryParam("enseres") String enseres, @QueryParam("mediosAudio") String mediosAudio, @QueryParam("laboratorio") Integer laboratory) {
        lab = laboratorioService.buscarLaboratorioPorId(laboratory);
        if (lab != null) {
            sala = new Sala(id, nombre, lab, mediosAudio, enseres);
            Set<Equipo> aux = equipoService.buscarTodosLosEquipos();
            Set<Equipo> listaEquipo = equipoService.buscarTodosLosEquipos();
            listaEquipo.clear();
            for (Equipo e : aux) {
                if (e.getSala().getId() == id) {
                    listaEquipo.add(e);
                }
            }
            sala.setEquipos(listaEquipo);
            salaService.guardarSala(sala);
            logger.debug("Sala guardada éxitosamente " + sala);
            return "Exitoso el guardado";

        }
        return "Something wrong bro";

    }

    /**
     * Obtener una sala
     *
     * @param id Identificacion de la sala
     * @return la sala buscada
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String obtener(@QueryParam("id") int id) {
        sala = salaService.buscarSalaPorId(id);
        if (sala != null) {
            logger.debug("Sala retornada " + sala);
            return sala.toString();
        }
        return "Sorry champ this Sala doesn't exist .|. ";
    }

}
