package org.sunnycake.aton.service.impl;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sunnycake.aton.dao.LaboratorioDAO;
import org.sunnycake.aton.dto.Laboratorio;
import org.sunnycake.aton.exception.ExcepcionConsulta;
import org.sunnycake.aton.service.LaboratorioService;

@Service("laboratorioService")
//Transactional crea una transacción cuando se inicia cada uno de los métodos
//y la cierra cuando termina el método
@Transactional
public class LaboratorioServiceImpl implements LaboratorioService {
private Logger logger = LogManager.getLogger(LaboratorioService.class);
	
	@Autowired
	private LaboratorioDAO dao;

	public Laboratorio buscarLaboratorioPorId(long id) {
		try {
			return dao.buscarLaboratorioPorId(id);
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al buscar la laboratorio a través del id: " + id, e);
			return null;
		}
	}

	public void guardarLaboratorio(Laboratorio laboratorio) {
		try {
			dao.guardarLaboratorio(laboratorio);
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al guardar la laboratorio: " + laboratorio, e);
		}

	}

	public void actualizarLaboratorio(Laboratorio laboratorio) {
		Laboratorio entidad;
		try {
			entidad = dao.buscarLaboratorioPorId(laboratorio.getId());
			if (entidad != null) {
				entidad.setAdministracion(laboratorio.getAdministracion());
				entidad.setNombre(laboratorio.getNombre());
				entidad.setUbicacion(laboratorio.getUbicacion());
			}
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al actualizar la laboratorio: " + laboratorio, e);
		}

	}

	public void eliminarLaboratorioPorId(long id) {
		try {
			dao.eliminarLaboratorioPorId(id);
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al actualizar la laboratorio con id: " + id, e);
		}

	}

	public Set<Laboratorio> buscarTodosLosLaboratorios() {
		try {
			return dao.obtenerTodos();
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al obtener todos los equipos", e);
			return null;
		}
	}

	public boolean esIdUnico(long id) {
		Laboratorio laboratorio = null;
		try {
			laboratorio = dao.buscarLaboratorioPorId(id);
		} catch (ExcepcionConsulta e) {
			logger.error("Ocurrió un error al buscar la laboratorio a través del id: " + id, e);
			e.printStackTrace();
		}
		return (laboratorio == null);
	}

    @Override
    public void guardarLaboratorio(long id, String ubicacion, String administracion, String nombre) {
        Laboratorio laboratorio = new Laboratorio(id, ubicacion, administracion, nombre);
        guardarLaboratorio(laboratorio);
    }
}
