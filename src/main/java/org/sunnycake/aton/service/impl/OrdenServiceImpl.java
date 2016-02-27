/**
 *
 */
package org.sunnycake.aton.service.impl;

import java.util.Date;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sunnycake.aton.dao.OrdenDAO;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Orden;
import org.sunnycake.aton.dto.OrdenPK;
import org.sunnycake.aton.dto.UsuarioWeb;
import org.sunnycake.aton.exception.ExcepcionConsulta;
import org.sunnycake.aton.service.OrdenService;

/**
 *
 * @author Camilo Sampedro
 *
 */
@Service("ordenService")
// Transactional crea una transacción cuando se inicia cada uno de los métodos
// y la cierra cuando termina el método
@Transactional
public class OrdenServiceImpl implements OrdenService {

    private Logger logger = LogManager.getLogger(OrdenService.class);

    @Autowired
    OrdenDAO dao;

    public Orden buscarOrdenPorClave(OrdenPK clave) {
        try {
            return dao.obtenerOrdenPorClave(clave);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al buscar el orden a través de la IP: " + clave, e);
            return null;
        }
    }

    public void guardarOrden(Orden orden) {
        try {
            dao.guardarOrden(orden);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al guardar el orden: " + orden, e);
        }
    }

    public void actualizarOrden(Orden orden) {
        Orden entidad;
        OrdenPK clave = orden.getId();
        try {
            entidad = dao.obtenerOrdenPorClave(clave);
            if (entidad != null) {
                entidad.setCodigosalida(orden.getCodigosalida());
                entidad.setComando(orden.getComando());
                entidad.setResultado(orden.getResultado());
                entidad.setUsuarioweb(orden.getUsuarioweb());
            }
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al actualizar el orden: " + orden, e);
        }
    }

    public void eliminarOrdenPorClave(OrdenPK clave) {
        try {
            dao.eliminarOrdenPorClave(clave);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al actualizar el orden: " + clave, e);
        }

    }

    public Set<Orden> buscarTodasLasOrdenes() {
        try {
            return dao.obtenerTodos();
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al obtener todos los ordens", e);
            return null;
        }
    }

    public boolean esClaveUnica(OrdenPK clave) {
        Orden orden = null;
        try {
            orden = dao.obtenerOrdenPorClave(clave);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al buscar el orden a través de la IP: " + clave, e);
            e.printStackTrace();
        }
        return (orden == null || clave == null);
    }

    @Override
    public void guardarOrden(Equipo pkEquipo, UsuarioWeb usuarioWeb, String comando, String resultado, int codigoSalida, boolean interrumpir, boolean sudo) {
        Orden orden = new Orden(pkEquipo, new Date(), usuarioWeb, comando, resultado, codigoSalida, interrumpir, sudo);
        guardarOrden(orden);
    }

    public OrdenDAO getDao() {
        return dao;
    }

    public void setDao(OrdenDAO dao) {
        this.dao = dao;
    }

    
}
