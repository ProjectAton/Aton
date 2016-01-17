/**
 * 
 */
package org.sunnycake.aton.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.SugerenciaDAO;
import org.sunnycake.aton.dto.Sugerencia;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author Camilo Sampedro
 *
 */
@Repository("sugerenciaDAO")
public class SugerenciaDAOImpl extends DAOAbstracto<Long, Sugerencia> implements SugerenciaDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.SugerenciaDAO#obtenerTodos()
	 */
	@Override
	public List<Sugerencia> obtenerTodos() throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		return getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.SugerenciaDAO#guardarSugerencia(org.sunnycake.aton
	 * .dto.Sugerencia)
	 */
	@Override
	public void guardarSugerencia(Sugerencia sugerencia) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		persist(sugerencia);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.SugerenciaDAO#obtenerSugerenciaPorClave(long)
	 */
	@Override
	public Sugerencia obtenerSugerenciaPorClave(long id) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.SugerenciaDAO#eliminarSugerenciaPorClave(long)
	 */
	@Override
	public void eliminarSugerenciaPorClave(long id) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		delete(obtenerSugerenciaPorClave(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.SugerenciaDAO#actualizarSugerencia(org.sunnycake.
	 * aton.dto.Sugerencia)
	 */
	@Override
	public void actualizarSugerencia(Sugerencia sugerencia) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		update(sugerencia);
	}

}
