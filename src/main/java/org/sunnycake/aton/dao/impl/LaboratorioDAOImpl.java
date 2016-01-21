package org.sunnycake.aton.dao.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.LaboratorioDAO;
import org.sunnycake.aton.dto.Laboratorio;
import org.sunnycake.aton.exception.ExcepcionConsulta;

@Repository("laboratorioDAO")
public class LaboratorioDAOImpl extends DAOAbstracto<Long, Laboratorio> implements LaboratorioDAO {

	/**
	 * Método que hace uso del DAOAbstracto para que se obtengan todos los
	 * elementos de la entidad
	 * 
	 * @see org.sunnycake.aton.dao.LaboratorioDAO.obtenerTodos()
	 */
	public Set<Laboratorio> obtenerTodos() throws ExcepcionConsulta {
		return getTodos();
	}

	/**
	 * Método que utiliza el DAOAbstracto para almacenar nuevos Laboratorios, no
	 * permite que el id ya exista.
	 * 
	 * @see org.sunnycake.aton.dao.LaboratorioDAO#guardarLaboratorio(Laboratorio)
	 */
	public void guardarLaboratorio(Laboratorio laboratorio) throws ExcepcionConsulta {
		guardarEntidad(laboratorio);
	}

	public Laboratorio buscarLaboratorioPorId(long idLaboratorio) throws ExcepcionConsulta {
		return getEntidadPorClave(idLaboratorio);
	}

	public void eliminarLaboratorioPorId(long id) throws ExcepcionConsulta {
		eliminarEntidad(buscarLaboratorioPorId(id));
	}

	public void actualizarLaboratorio(Laboratorio laboratorio) throws ExcepcionConsulta {
		actualizarEntidad(laboratorio);
	}
}
