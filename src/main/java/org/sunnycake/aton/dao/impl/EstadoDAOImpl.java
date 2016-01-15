/**
 * 
 */
package org.sunnycake.aton.dao.impl;

import java.util.List;

import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.EstadoDAO;
import org.sunnycake.aton.dto.Estado;
import org.sunnycake.aton.dto.EstadoPK;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author camilo
 *
 */
public class EstadoDAOImpl extends DAOAbstracto<EstadoPK, Estado> implements EstadoDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.EstadoDAO#obtenerTodos()
	 */
	@Override
	public List<Estado> obtenerTodos() throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		return getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.EstadoDAO#guardarEstado(org.sunnycake.aton.dto.
	 * Estado)
	 */
	@Override
	public void guardarEstado(Estado estado) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		persist(estado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.EstadoDAO#actualizarEstado(org.sunnycake.aton.dto.
	 * Estado)
	 */
	@Override
	public void actualizarEstado(Estado estado) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		update(estado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.EstadoDAO#obtenerEstadoPorClave(org.sunnycake.aton
	 * .dto.EstadoPK)
	 */
	@Override
	public Estado obtenerEstadoPorClave(EstadoPK clave) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		return getByKey(clave);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.EstadoDAO#eliminarEstadoPorClave(org.sunnycake.
	 * aton.dto.Estado)
	 */
	@Override
	public void eliminarEstadoPorClave(EstadoPK estado) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		delete(obtenerEstadoPorClave(estado));
	}

}
