/**
 * 
 */
package org.sunnycake.aton.dao.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.UsuarioWebDAO;
import org.sunnycake.aton.dto.UsuarioWeb;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author camilo
 *
 */
@Repository("usuarioWebDAO")
public class UsuarioWebDAOImpl extends DAOAbstracto<String, UsuarioWeb> implements UsuarioWebDAO {

	/**
	 * Método que hace uso del DAOAbstracto para que se obtengan todos los
	 * elementos de la entidad
	 * 
	 * @see org.sunnycake.aton.dao.UsuarioWebDAO.obtenerTodos()
	 */
	@Override
	public Set<UsuarioWeb> obtenerTodos() throws ExcepcionConsulta {
		return getTodos();
	}

	/**
	 * Método que utiliza el DAOAbstracto para almacenar nuevos Usuarios Web, no
	 * permite que el nombre de usuario ya exista.
	 * 
	 * @see org.sunnycake.aton.dao.UsuarioWebDAO#guardarUsuarioWeb(UsuarioWeb)
	 */
	@Override
	public void guardarUsuarioWeb(UsuarioWeb usuario) throws ExcepcionConsulta {
		guardarEntidad(usuario);
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
		return getEntidadPorClave(usuario);
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
		eliminarEntidad(obtenerUsuarioWebPorUsuario(usuario));
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
		actualizarEntidad(usuario);
	}

}
