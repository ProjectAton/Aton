/**
 *
 */
package org.sunnycake.aton.service.impl;

import com.owlike.genson.Genson;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sunnycake.aton.dao.SalaDAO;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Laboratorio;
import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.exception.ExcepcionConsulta;
import org.sunnycake.aton.service.SalaService;

/**
 *
 *
 * @author Camilo Sampedro
 *
 */
@Service("salaService")
// Transactional crea una transacción cuando se inicia cada uno de los métodos
// y la cierra cuando termina el método
@Transactional
public class SalaServiceImpl implements SalaService {

    private Logger logger = LogManager.getLogger(SalaService.class);

    @Autowired
    SalaDAO dao;

    public Sala buscarSalaPorId(int id) {
        try {
            return dao.buscarSalaPorId(id);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al buscar la sala a través del id: " + id, e);
            return null;
        }
    }

    public void guardarSala(Sala sala) {
        try {
            dao.guardarSala(sala);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al guardar la sala: " + sala, e);
        }

    }

    public void actualizarSala(Sala sala) {
        Sala entidad;
        try {
            entidad = dao.buscarSalaPorId(sala.getId());
            if (entidad != null) {
                entidad.setEnseres(sala.getEnseres());
                entidad.setLaboratorio(sala.getLaboratorio());
                entidad.setMediosaudiovisuales(sala.getMediosaudiovisuales());
            }
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al actualizar la sala: " + sala, e);
        }

    }

    public void eliminarSalaPorId(int id) {
        try {
            dao.eliminarSalaPorId(id);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al actualizar la sala con id: " + id, e);
        }

    }

    public Set<Sala> buscarTodasLasSalas() {
        try {
            return dao.obtenerTodos();
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al obtener todos los equipos", e);
            return null;
        }
    }

    public boolean esIdUnico(int id) {
        Sala sala = null;
        try {
            sala = dao.buscarSalaPorId(id);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al buscar la sala a través del id: " + id, e);
            e.printStackTrace();
        }
        return (sala == null);
    }

    @Override
    public void guardarSala(int id, String nombre, Laboratorio laboratorio, String mediosAudiovisuales, String enseres) {
        Sala sala = new Sala(id, nombre, laboratorio, mediosAudiovisuales, enseres);
        guardarSala(sala);
    }

    public SalaDAO getDao() {
        return dao;
    }

    public void setDao(SalaDAO dao) {
        this.dao = dao;
    }

    @Override
    public Set<Sala> buscarTodasLasSalasSimples() {
        Set<Sala> salas = buscarTodasLasSalas();
        Set<Sala> salasNuevas = new HashSet<>();
        
        for(Sala sala : salas){
            Set<Equipo> equipos= new HashSet<>();
            for (Equipo equipo: sala.getEquipos()){
                equipos.add(new Equipo(equipo.getMac(), equipo.getNombre(), "", "", equipo.getIp(), null, equipo.getDescripcion(), equipo.getMensaje(), equipo.getSeleccionado()));
            }
            salasNuevas.add(new Sala( sala.getId(), null, sala.getNombre(), sala.getMediosaudiovisuales(), sala.getEnseres(), equipos));
            logger.debug("Sala: " + sala);
        }
        
        return salasNuevas;        
    }
    
    

}
