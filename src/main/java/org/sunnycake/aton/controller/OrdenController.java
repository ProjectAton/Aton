package org.sunnycake.aton.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Orden;
import org.sunnycake.aton.dto.UsuarioWeb;
import org.sunnycake.aton.service.EquipoService;
import org.sunnycake.aton.service.OrdenService;
import org.sunnycake.aton.service.UsuarioWebService;
import org.sunnycake.aton.service.impl.EquipoServiceImpl;
import org.sunnycake.aton.service.impl.OrdenServiceImpl;
import org.sunnycake.aton.service.impl.UsuarioWebServiceImpl;

/**
 * Controlador de Orden
* @author Julian Arango
*
*/
@Component
@Path("ordenes")
public class OrdenController {
    private final Logger logger = LogManager.getLogger(EquipoController.class.getName());

    @Autowired
    OrdenService ordenService;
    @Autowired
    UsuarioWebService usuarioWebService;
    @Autowired
    EquipoService equipoService;
    Orden orden;
    Equipo equipo;
    UsuarioWeb usuarioWeb;
    /**
     * El metodo crear crea una orden y la almacena
     * para esto debe existir un equipo y un usuario que ejecute la orden
     * @param usuario usuario que ejecuta la orden
     * @param mac equipo al cual va dirigida la orden
     * @param comando comando a ejecutar
     * @param sudo tiene permisos de superusuario
     * @return Mensaje del tipo txt con el resultado de almacenar la orden
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    public String crear(@QueryParam("usuarioWeb") String usuario,@QueryParam("mac") String mac,@QueryParam("comando") String comando,@QueryParam("sudo") boolean sudo){
        Date fecha = Calendar.getInstance().getTime();
        logger.log(Level.INFO, "El usuario: {0} esta ejecutando el comando: {1} el dia: {2} sudo: {3}", new Object[]{usuarioWeb, comando, fecha.toString(), sudo});
        usuarioWeb = usuarioWebService.obtenerUsuarioWebPorUsuario(usuario);
        equipo=equipoService.buscarEquipoPorIp(mac);
        if(usuarioWeb==null || equipo==null){
            return "Pero pa que te traje si no existis, o tu mac no esta registrada.\n"
                    + "Boludo(a)";
        }
        //Las variables codigo salida e interrumpir todavia no se diligencian por lo que sus valores se dejan en nulo
        int codigoSalida = 0;
        boolean interrumpir=false;
        orden=new Orden(equipo, fecha, usuarioWeb, comando, usuario, codigoSalida, interrumpir, sudo);
        ordenService.guardarOrden(orden);
        return "Echeee no joda. tu orden se ejecuto pura chepa";
    }
    /**
     * Metodo para obtener las ordenes dado un determinado equipo
     * @param mac id del equipo al que se le desean consultar sus ordenes
     * @return una lista de ordenes de un equipo
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orden> consultarPorEquipo(@QueryParam("mac") String mac){
        Set<Orden> set =ordenService.buscarTodasLasOrdenes();
        List<Orden> lista =new ArrayList<>();
        logger.log(Level.INFO, "Me pidieron todas las ordenes de el equipo :"+mac);
        for(Orden o:set){
            if(o.getEquipo().getIp().equals(mac)){
                lista.add(o);
            }
        }
        return lista;
        
        
    }
    
    
}
