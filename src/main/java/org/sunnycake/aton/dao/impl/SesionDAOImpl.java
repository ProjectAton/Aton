/**
 * 
 */
package org.sunnycake.aton.dao.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.SesionDAO;
import org.sunnycake.aton.dto.Sesion;
import org.sunnycake.aton.dto.SesionPK;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author camilo
 *
 */
@Repository("sesionDAO")
public class SesionDAOImpl extends DAOAbstracto<SesionPK, Sesion> implements SesionDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.SesionDAO#obtenerTodos()
	 */
	@Override
	public Set<Sesion> obtenerTodos() throws ExcepcionConsulta {
		return getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.SesionDAO#guardarSesion(org.sunnycake.aton.dto.
	 * Sesion)
	 */
	@Override
	public void guardarSesion(Sesion sesion) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		persist(sesion);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.SesionDAO#obtenerSesion(org.sunnycake.aton.dto.
	 * SesionPK)
	 */
	@Override
	public Sesion obtenerSesionPorClave(SesionPK claveP) throws ExcepcionConsulta {
		return getByKey(claveP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.SesionDAO#eliminarSesion(org.sunnycake.aton.dto.
	 * Sesion)
	 */
	@Override
	public void eliminarSesionPorClave(SesionPK sesion) throws ExcepcionConsulta {
		delete(obtenerSesionPorClave(sesion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.SesionDAO#actualizarSesion(org.sunnycake.aton.dto.
	 * Sesion)
	 */
	@Override
	public void actualizarSesion(Sesion sesion) throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		actualizarSesion(sesion);
	}
}
