/**
 * 
 */
package org.sunnycake.aton.dao.impl;

import java.util.List;

import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.OrdenDAO;
import org.sunnycake.aton.dto.Orden;
import org.sunnycake.aton.dto.OrdenPK;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author camilo
 *
 */
public class OrdenDAOImpl extends DAOAbstracto<OrdenPK, Orden> implements OrdenDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.OrdenDAO#obtenerTodos()
	 */
	@Override
	public List<Orden> obtenerTodos() throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		return getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.OrdenDAO#guardarOrden(org.sunnycake.aton.dto.
	 * Orden)
	 */
	@Override
	public void guardarOrden(Orden orden) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		persist(orden);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.OrdenDAO#actualizarOrden(org.sunnycake.aton.dto.
	 * Orden)
	 */
	@Override
	public void actualizarOrden(Orden orden) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		update(orden);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.OrdenDAO#obtenerOrdenPorClave(org.sunnycake.aton.
	 * dto.OrdenPK)
	 */
	@Override
	public Orden obtenerOrdenPorClave(OrdenPK clave) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		return getByKey(clave);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.OrdenDAO#eliminarOrdenPorClave(org.sunnycake.aton.
	 * dto.Orden)
	 */
	@Override
	public void eliminarOrdenPorClave(OrdenPK ordenpk) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		delete(obtenerOrdenPorClave(ordenpk));
	}

}
