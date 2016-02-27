/**
 *
 */
package org.sunnycake.aton.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.SugerenciaDAO;
import org.sunnycake.aton.dto.Sugerencia;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author Camilo Sampedro
 *
 */
@Repository("sugerenciaDAO")
public class SugerenciaDAOImpl extends DAOAbstracto<Long, Sugerencia> implements SugerenciaDAO {

    /**
     * Método que hace uso del DAOAbstracto para que se obtengan todos los
     * elementos de la entidad
     *
     * @see org.sunnycake.aton.dao.SugerenciaDAO.obtenerTodos()
     * @throws ExcepcionConsulta
     */
    @Override
    public Set<Sugerencia> obtenerTodos() throws ExcepcionConsulta {
        return getTodos();
    }

    /**
     * Método que utiliza el DAOAbstracto para almacenar nuevas Sugerencias, no
     * permite que el id ya exista.
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     * @see org.sunnycake.aton.dao.SalaDAO#guardarSala(Sala)
     */
    @Override
    public void guardarSugerencia(Sugerencia sugerencia) throws ExcepcionConsulta {
        guardarEntidad(sugerencia);
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.SugerenciaDAO#obtenerSugerenciaPorClave(long)
     */
    @Override
    public Sugerencia obtenerSugerenciaPorClave(long id) throws ExcepcionConsulta {
        // TODO Auto-generated method stub
        return getEntidadPorClave(id);
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.SugerenciaDAO#eliminarSugerenciaPorClave(long)
     */
    @Override
    public void eliminarSugerenciaPorClave(long id) throws ExcepcionConsulta {
        // TODO Auto-generated method stub
        eliminarEntidad(obtenerSugerenciaPorClave(id));
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sunnycake.aton.dao.SugerenciaDAO#actualizarSugerencia(org.sunnycake.
	 * aton.dto.Sugerencia)
     */
    @Override
    public void actualizarSugerencia(Sugerencia sugerencia) throws ExcepcionConsulta {
        // TODO Auto-generated method stub
        actualizarEntidad(sugerencia);
    }

}
