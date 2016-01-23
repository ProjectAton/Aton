/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sunnycake.aton.controller;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.service.EquipoService;

/**
 *
 * @author camilo
 */
@Path("EquipoServlet")
public class EquipoController {

    private final Logger logger = LogManager.getLogger(EquipoController.class.getName());

    @Autowired
    EquipoService equipoService;

    @POST
    @Produces(MediaType.TEXT_HTML)
    public String crear(@QueryParam("ip") String ip, @QueryParam("usuario") String usuario, @QueryParam("password") String password) {
        logger.debug("Guardando el equipo: ip{" + ip + "} usuario{" + usuario + "} contraseña{" + password + "} ");
        equipoService.guardarEqupo(usuario, password, ip);
        return "Equipo creado";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Equipo> obtener() {
        logger.debug("Obteniendo todos los equipos");
        List<Equipo> lista = new ArrayList<>();

        for (Equipo equipo : equipoService.buscarTodosLosEquipos()) {
            String mac = equipo.getMac();
            String nombre = equipo.getNombre();
            String ip = equipo.getIp();
            Sala sala = equipo.getSala();
            String descripcion = equipo.getDescripcion();
            // No mostrar los campos usuario, contraseña y mensaje
            Equipo equipoNuevo = new Equipo(mac, nombre, "", "", ip, sala, descripcion, "", false);
            lista.add(equipoNuevo);
        }

        return lista;
    }
}
