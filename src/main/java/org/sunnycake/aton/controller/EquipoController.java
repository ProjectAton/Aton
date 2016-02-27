/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sunnycake.aton.controller;

import com.owlike.genson.Genson;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.service.EquipoService;
import org.sunnycake.aton.service.SalaService;

/**
 * Controlador de Equipo.
 *
 * @author Julian Arango, Camilo Sampedro
 */
@Component
@Path("equipos")
public class EquipoController {

    /**
     * Objeto para realizar logs de los procesos realizados.
     */
    private final Logger logger = LogManager.getLogger(EquipoController.class.getName());
    
    /**
     * Servicio de equipos.
     */
    @Autowired
    EquipoService equipoService;
    
    /**
     * Servicio de salas.
     */
    @Autowired
    SalaService salaService;

    /**
     * Guarda un equipo 
     *
     * @param ip ip del equipo
     * @param usuario usuario para conectar el equipo por ssh
     * @param password password para conectar el equipo por ssh
     * @return Mensaje de respuesta
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    public String crear(@QueryParam("ip") String ip, @QueryParam("usuario") String usuario, @QueryParam("password") String password) {
        logger.debug("Guardando el equipo: ip{" + ip + "} usuario{" + usuario + "} contraseña{" + password + "} ");
        equipoService.guardarEquipo(usuario, password, ip);
        return "Equipo creado";
    }

    /**
     * Obtiene la lista de equipos y la devuelve en formato JSON
     *
     * @return Lista de equipos de la base de datos.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String obtener() {
        logger.debug("Obteniendo todos los equipos");
        Set<Sala> salas= salaService.buscarTodasLasSalasSimples();
        logger.debug("Obtenidas.");
        Genson genson = new Genson();
        String retorno = genson.serialize(salas);
        logger.debug(retorno);
        return retorno;
    }
    
}
