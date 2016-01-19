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
import org.sunnycake.aton.dao.OrdenDAO;
import org.sunnycake.aton.dto.Orden;
import org.sunnycake.aton.dto.OrdenPK;
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
	private OrdenDAO ordenDAO;

	public Orden buscarOrdenPorClave(OrdenPK clave) {
		try {
			return ordenDAO.obtenerOrdenPorClave(clave);
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al buscar el orden a través de la IP: " + clave, e);
			return null;
		}
	}

	public void guardarOrden(Orden orden) {
		try {
			ordenDAO.guardarOrden(orden);
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al guardar el orden: " + orden, e);
		}
	}

	public void actualizarOrden(Orden orden) {
		Orden entidad;
		OrdenPK clave = new OrdenPK();
		clave.setPkFecha(orden.getPkFecha());
		clave.setPkEquipo(orden.getPkEquipo());
		try {
			entidad = ordenDAO.obtenerOrdenPorClave(clave);
			if (entidad != null) {
				entidad.setCodigoSalida(orden.getCodigoSalida());
				entidad.setComando(orden.getComando());
				entidad.setResultado(orden.getResultado());
				entidad.setUsuarioWeb(orden.getUsuarioWeb());
			}
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al actualizar el orden: " + orden, e);
		}
	}

	public void eliminarOrdenPorClave(OrdenPK clave) {
		try {
			ordenDAO.eliminarOrdenPorClave(clave);
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al actualizar el orden: " + clave, e);
		}

	}

	public List<Orden> buscarTodasLasOrdenes() {
		try {
			return ordenDAO.obtenerTodos();
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al obtener todos los ordens", e);
			return null;
		}
	}

	public boolean esClaveUnica(OrdenPK clave) {
		Orden orden = null;
		try {
			orden = ordenDAO.obtenerOrdenPorClave(clave);
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al buscar el orden a través de la IP: " + clave, e);
			e.printStackTrace();
		}
		return (orden == null || clave == null);
	}

}
