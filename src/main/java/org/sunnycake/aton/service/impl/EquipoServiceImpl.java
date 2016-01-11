/**
 * 
 */
package org.sunnycake.aton.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sunnycake.aton.dao.EquipoDAO;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.exception.ExcepcionConsulta;
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

	public Equipo buscarEquipoPorMac(String mac) {
		try {
			return dao.buscarEquipoPorMac(mac);
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al buscar el equipo a través de la MAC: " + mac, e);
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
			entidad = dao.buscarEquipoPorMac(equipo.getMac());
			if (entidad != null) {
				entidad.setDescripcion(equipo.getDescripcion());
				entidad.setIp(equipo.getIp());
				entidad.setSala(equipo.getSala());
			}
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al actualizar el equipo: " + equipo, e);
		}
	}

	public void eliminarEquipoPorMac(String mac) {
		try {
			dao.eliminarEquipoPorMac(mac);
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al actualizar el equipo: " + mac, e);
		}

	}

	public List<Equipo> buscarTodosLosEquipos() {
		try {
			return dao.obtenerTodos();
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al obtener todos los equipos", e);
			return null;
		}
	}

	public Equipo buscarEquipoPorIp(String ip) {
		try {
			return dao.buscarEquipoPorIp(ip);
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al obtener todos los equipos", e);
			return null;
		}
	}

	public boolean esMacUnica(String mac) {
		Equipo equipo = null;
		try {
			equipo = dao.buscarEquipoPorMac(mac);
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al buscar el equipo a través de la MAC: " + mac, e);
			e.printStackTrace();
		}
		return (equipo == null || mac == null);
	}

}
