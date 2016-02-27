/**
 *
 */
package org.sunnycake.aton.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.SalaDAO;
import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author camilo
 *
 */
@Repository("salaDAO")
public class SalaDAOImpl extends DAOAbstracto<Integer, Sala> implements SalaDAO {

    /**
     * Método que hace uso del DAOAbstracto para que se obtengan todos los
     * elementos de la entidad
     *
     * @see org.sunnycake.aton.dao.SalaDAO.obtenerTodos()
     */
    @Override
    public Set<Sala> obtenerTodos() throws ExcepcionConsulta {
        return getTodos();
    }

    /**
     * Método que utiliza el DAOAbstracto para almacenar nuevos Equipos, no
     * permite que la ip ya exista.
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     * @see org.sunnycake.aton.dao.SalaDAO#guardarSala(Sala)
     */
    @Override
    public void guardarSala(Sala sala) throws ExcepcionConsulta {
        guardarEntidad(sala);
    }

    @Override
    public Sala buscarSalaPorId(int idSala) throws ExcepcionConsulta {
        return getEntidadPorClave(idSala);
    }

    @Override
    public void eliminarSalaPorId(int id) throws ExcepcionConsulta {
        eliminarEntidad(buscarSalaPorId(id));
    }

    @Override
    public void actualizar(Sala sala) throws ExcepcionConsulta {
        actualizarEntidad(sala);
    }

}
