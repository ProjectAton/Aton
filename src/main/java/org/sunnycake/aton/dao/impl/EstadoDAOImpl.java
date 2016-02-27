/**
 *
 */
package org.sunnycake.aton.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.EstadoDAO;
import org.sunnycake.aton.dto.Estado;
import org.sunnycake.aton.dto.EstadoPK;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author camilo
 *
 */
@Repository("estadoDAO")
public class EstadoDAOImpl extends DAOAbstracto<EstadoPK, Estado> implements EstadoDAO {

    /**
     * Método que hace uso del DAOAbstracto para que se obtengan todos los
     * elementos de la entidad
     *
     * @see org.sunnycake.aton.dao.EstadoDAO#obtenerTodos()
     */
    @Override
    public Set<Estado> obtenerTodos() throws ExcepcionConsulta {
        return getTodos();
    }

    /**
     * Método que utiliza el DAOAbstracto para almacenar nuevos Estados, no
     * permite que la clave primaria ya exista.
     *
     * @see org.sunnycake.aton.dao.EstadoDAO#guardarEstado(Estado)
     */
    @Override
    public void guardarEstado(Estado estado) throws ExcepcionConsulta {
        guardarEntidad(estado);
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
        actualizarEntidad(estado);
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
        return getEntidadPorClave(clave);
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
        eliminarEntidad(obtenerEstadoPorClave(estado));
    }

}
