/**
 * 
 */
package org.sunnycake.aton.dao.impl;

import java.util.List;

import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.UsuarioWebDAO;
import org.sunnycake.aton.dto.UsuarioWeb;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author camilo
 *
 */
public class UsuarioWebDAOImpl extends DAOAbstracto<String, UsuarioWeb> implements UsuarioWebDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.UsuarioWebDAO#obtenerTodos()
	 */
	@Override
	public List<UsuarioWeb> obtenerTodos() throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		return getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.UsuarioWebDAO#guardarUsuarioWeb(org.sunnycake.aton
	 * .dto.UsuarioWeb)
	 */
	@Override
	public void guardarUsuarioWeb(UsuarioWeb usuario) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		persist(usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.UsuarioWebDAO#obtenerUsuarioWebPorUsuario(java.
	 * lang.String)
	 */
	@Override
	public UsuarioWeb obtenerUsuarioWebPorUsuario(String usuario) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		return getByKey(usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.UsuarioWebDAO#eliminarUsuarioWebPorUsuario(org.
	 * sunnycake.aton.dto.UsuarioWeb)
	 */
	@Override
	public void eliminarUsuarioWebPorUsuario(String usuario) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		delete(obtenerUsuarioWebPorUsuario(usuario));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.UsuarioWebDAO#actualizarUsuarioWeb(org.sunnycake.
	 * aton.dto.UsuarioWeb)
	 */
	@Override
	public void actualizarUsuarioWeb(UsuarioWeb usuario) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		update(usuario);
	}

}
