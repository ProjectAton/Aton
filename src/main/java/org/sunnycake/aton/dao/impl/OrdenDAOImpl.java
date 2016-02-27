/**
 *
 */
package org.sunnycake.aton.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.OrdenDAO;
import org.sunnycake.aton.dto.Orden;
import org.sunnycake.aton.dto.OrdenPK;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author Camilo Sampedro
 *
 */
@Repository("ordenDAO")
public class OrdenDAOImpl extends DAOAbstracto<OrdenPK, Orden> implements OrdenDAO {

    /**
     * Método que hace uso del DAOAbstracto para que se obtengan todos los
     * elementos de la entidad
     *
     * @see org.sunnycake.aton.dao.OrdenDAO.obtenerTodos()
     */
    @Override
    public Set<Orden> obtenerTodos() throws ExcepcionConsulta {
        return getTodos();
    }

    /**
     * Método que utiliza el DAOAbstracto para almacenar nuevas Ordenes, no
     * permite que la clave ya exista.
     *
     * @see org.sunnycake.aton.dao.OrdenDAO#guardarOrden(Orden)
     */
    @Override
    public void guardarOrden(Orden orden) throws ExcepcionConsulta {
        guardarEntidad(orden);
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
        actualizarEntidad(orden);
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
        return getEntidadPorClave(clave);
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
        eliminarEntidad(obtenerOrdenPorClave(ordenpk));
    }

}
