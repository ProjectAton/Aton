/**
 *
 */
package org.sunnycake.aton.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.sunnycake.aton.dao.EquipoDAO;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.exception.ExcepcionConsulta;
import org.sunnycake.aton.exec.Ejecucion;
import org.sunnycake.aton.exec.Tarea;
import org.sunnycake.aton.service.EquipoService;

/**
 *
 * @author Camilo Sampedro
 *
 */
@Service("equipoService")
// Transactional crea una transacción cuando se inicia cada uno de los métodos
// y la cierra cuando termina el método
@Transactional
public class EquipoServiceImpl implements EquipoService {

    private Logger logger = LogManager.getLogger(EquipoService.class);

    @Autowired
    private EquipoDAO dao;

    public Equipo buscarEquipoPorIp(String ip) {
        try {
            return dao.buscarEquipoPorIp(ip);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al buscar el equipo a través de la IP: " + ip, e);
            return null;
        }
    }

    public void guardarEquipo(Equipo equipo) {
        try {
            dao.guardarEquipo(equipo);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al guardar el equipo: " + equipo, e);
        }
    }

    public void actualizarEquipo(Equipo equipo) {
        Equipo entidad;
        try {
            entidad = dao.buscarEquipoPorIp(equipo.getIp());
            if (entidad != null) {
                entidad.setNombre(equipo.getNombre());
                entidad.setUsuario(equipo.getUsuario());
                entidad.setPassword(equipo.getPassword());
                entidad.setDescripcion(equipo.getDescripcion());
                entidad.setMac(equipo.getMac());
                entidad.setSala(equipo.getSala());
            }
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al actualizar el equipo: " + equipo, e);
        }
    }

    public void eliminarEquipoPorIp(String mac) {
        try {
            dao.eliminarEquipoPorIp(mac);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al actualizar el equipo: " + mac, e);
        }

    }

    public Set<Equipo> buscarTodosLosEquipos() {
        try {
            return dao.obtenerTodos();
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al obtener todos los equipos", e);
            return null;
        }
    }

    public Equipo buscarEquipoPorNombre(String nombre) {
        try {
            return dao.buscarEquipoPorNombre(nombre);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al obtener todos los equipos", e);
            return null;
        }
    }

    public boolean esIpUnica(String mac) {
        Equipo equipo = null;
        try {
            equipo = dao.buscarEquipoPorIp(mac);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al buscar el equipo a través de la IP: " + mac, e);
            e.printStackTrace();
        }
        return (equipo == null || mac == null);
    }

    @Override
    public void guardarEquipo(String mac, String nombre, String usuario, String password, String ip, Sala sala, String descripcion, String mensaje, boolean seleccionado) {
        Equipo equipo = new Equipo(mac, nombre, usuario, password, ip, sala, descripcion, mensaje, seleccionado);
        guardarEquipo(equipo);
    }

    @Override
    public void guardarEquipo(String usuario, String password, String ip) {

        Equipo equipo = new Equipo(usuario, password, ip);
        logger.debug("Buscando la dirección MAC de " + equipo.getIp());
        Tarea tarea = Ejecucion.obtenerMac(equipo);
        logger.debug(tarea.getExecQueue().retornarBuffer());

        if (tarea.getExecQueue() == null || "Error".equals(tarea.getExecQueue().retornarBuffer())
                || "".equals(tarea.getExecQueue().retornarBuffer())) {
            logger.error("IP no encontrada " + equipo.getIp());
            return;
        }

        String mac = tarea.getExecQueue().retornarBuffer().trim();
        logger.debug("Mac encontrada: " + mac);
        equipo.setMac(mac);
        guardarEquipo(equipo);
    }

}
