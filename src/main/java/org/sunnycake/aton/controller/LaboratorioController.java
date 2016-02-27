package org.sunnycake.aton.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sunnycake.aton.dto.Laboratorio;
import org.sunnycake.aton.service.LaboratorioService;
import org.sunnycake.aton.service.SalaService;

/**
 * Controlador de Laboratorio
 * @author Julian Arango
 *
 */
@Component
@Path("laboratorios")
public class LaboratorioController {

    private final Logger logger = LogManager.getLogger(EquipoController.class.getName());
    @Autowired
    LaboratorioService laboratorioServiceImpl;
    @Autowired
    SalaService serviceImpl;
    Laboratorio laboratorio;

    /**
     * El metodo crea y guarda un laboratorio
     *
     * @param id id unico del grupo
     * @param ubicacion ubicacion fisica del laboratorio
     * @param nombre nombre del laboratorio
     * @param administracion quien administra este laboratorio
     * @return texto en formato txt con un mensaje de confirmacion
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    public String crear(@QueryParam("id") Integer id, @QueryParam("ubicacion") String ubicacion, @QueryParam("nombre") String nombre, @QueryParam("administracion") String administracion) {
        logger.log(Level.FATAL,
                "Insertanto laboratorio id: {0}", id.toString());
        laboratorio = new Laboratorio(id, ubicacion, administracion, nombre);

        laboratorioServiceImpl.guardarLaboratorio(laboratorio);

        return "Succes!!!";

    }

    /**
     * Metodo para eliminar un laboratorio
     *
     * @param id Id del laboratoro a eliminar
     * @return texto en mensaje txt con informacion del exito de la transaccion
     * en caso de error se lleva al log
     */
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    public String eliminar(@QueryParam("id") Integer id) {
        laboratorioServiceImpl.eliminarLaboratorioPorId(id);
        logger.log(Level.INFO, "Se elimino el laboratorio id: {0} \n"
                + "en caso de que no se eliminara ver los logs para el detalle del error", id.toString());
        return "Bravo campeon, te mereces una pilsen";

    }

    /**
     * Metodo que obtiene todos los laboratorios registrados
     *
     * @return una lista con los laboratorios disponibles
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Laboratorio> obtener() {
        logger.log(Level.INFO, "Me consultan todos los laboratorios ojo con un DDOS");
        Set<Laboratorio> lista = laboratorioServiceImpl.buscarTodosLosLaboratorios();
        List<Laboratorio> retorno = new ArrayList<>();
        for (Laboratorio lab : lista) {
            retorno.add(lab);
        }
        return retorno;

    }

}
