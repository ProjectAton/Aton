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

	/**
	 * Método que hace uso del DAOAbstracto para que se obtengan todos los
	 * elementos de la entidad
	 * 
	 * @see org.sunnycake.aton.dao.SesionDAO.obtenerTodos()
	 */
	@Override
	public Set<Sesion> obtenerTodos() throws ExcepcionConsulta {
		return getTodos();
	}

	/**
	 * Método que utiliza el DAOAbstracto para almacenar nuevas Sesiones, no
	 * permite que la clave ya exista.
	 * 
	 * @see org.sunnycake.aton.dao.SesionDAO#guardarSesion(Sesion)
	 */
	@Override
	public void guardarSesion(Sesion sesion) throws ExcepcionConsulta {
		guardarEntidad(sesion);

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
		return getEntidadPorClave(claveP);
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
		eliminarEntidad(obtenerSesionPorClave(sesion));
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
