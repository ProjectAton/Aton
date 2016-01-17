/**
 * 
 */
package org.sunnycake.aton.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.RolDAO;
import org.sunnycake.aton.dto.Rol;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author Camilo Sampedro
 *
 */
@Repository("rolDAO")
public class RolDAOImpl extends DAOAbstracto<Integer, Rol> implements RolDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.TipoUsuarioDAO#obtenerTodos()
	 */
	@Override
	public List<Rol> obtenerTodos() throws ExcepcionConsulta {
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
	public void guardar(Rol tipo) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		persist(tipo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.TipoUsuarioDAO#obtenerTipoUsuarioPorId(int)
	 */
	@Override
	public Rol obtener(int id) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.TipoUsuarioDAO#eliminarTipoUsuarioPorId(int)
	 */
	@Override
	public void eliminar(int id) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		delete(obtener(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.TipoUsuarioDAO#actualizarTipoUsuario(org.sunnycake
	 * .aton.dto.TipoUsuario)
	 */
	@Override
	public void actualizar(Rol tipo) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		update(tipo);
	}

}
