package org.sunnycake.aton.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.LaboratorioDAO;
import org.sunnycake.aton.dto.Laboratorio;
import org.sunnycake.aton.exception.ExcepcionConsulta;

@Repository("laboratorioDAO")
public class LaboratorioDAOImpl extends DAOAbstracto<Long, Laboratorio> implements LaboratorioDAO {

	public List<Laboratorio> obtenerTodos() throws ExcepcionConsulta {
		return getAll();
	}

	public void guardarLaboratorio(Laboratorio laboratorio) throws ExcepcionConsulta {
		persist(laboratorio);
	}

	public Laboratorio buscarLaboratorioPorId(long idLaboratorio) throws ExcepcionConsulta {
		return getByKey(idLaboratorio);
	}

	public void eliminarLaboratorioPorId(long id) throws ExcepcionConsulta {
		delete(buscarLaboratorioPorId(id));
	}

	public void actualizarLaboratorio(Laboratorio laboratorio) throws ExcepcionConsulta {
		update(laboratorio);
	}
}
