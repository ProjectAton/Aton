/**
 * 
 */
package org.sunnycake.aton.dao.impl;

import java.util.List;

import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.TipoUsuarioDAO;
import org.sunnycake.aton.dto.TipoUsuario;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author Camilo Sampedro
 *
 */
public class TipoUsuarioDAOImpl extends DAOAbstracto<Integer, TipoUsuario> implements TipoUsuarioDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.TipoUsuarioDAO#obtenerTodos()
	 */
	@Override
	public List<TipoUsuario> obtenerTodos() throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		return getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.TipoUsuarioDAO#guardarTipoUsuario(org.sunnycake.
	 * aton.dto.TipoUsuario)
	 */
	@Override
	public void guardarTipoUsuario(TipoUsuario tipo) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		persist(tipo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.TipoUsuarioDAO#obtenerTipoUsuarioPorId(int)
	 */
	@Override
	public TipoUsuario obtenerTipoUsuarioPorId(int id) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.TipoUsuarioDAO#eliminarTipoUsuarioPorId(int)
	 */
	@Override
	public void eliminarTipoUsuarioPorId(int id) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		delete(obtenerTipoUsuarioPorId(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.TipoUsuarioDAO#actualizarTipoUsuario(org.sunnycake
	 * .aton.dto.TipoUsuario)
	 */
	@Override
	public void actualizarTipoUsuario(TipoUsuario tipo) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		update(tipo);
	}

}
