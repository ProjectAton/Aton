package org.sunnycake.aton.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.LaboratorioDAO;
import org.sunnycake.aton.dto.Laboratorio;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * DAO para la tabla Laboratorio en la base de datos.
 * @author Camilo Sampedro
 */
@Repository("laboratorioDAO")
public class LaboratorioDAOImpl extends DAOAbstracto<Integer, Laboratorio> implements LaboratorioDAO {

    /**
     * Método que hace uso del DAOAbstracto para que se obtengan todos los
     * elementos de la entidad
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     * @see org.sunnycake.aton.dao.LaboratorioDAO.obtenerTodos()
     */
    @Override
    public Set<Laboratorio> obtenerTodos() throws ExcepcionConsulta {
        return getTodos();
    }

    /**
     * Método que utiliza el DAOAbstracto para almacenar nuevos Laboratorios, no
     * permite que el id ya exista.
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     * @see
     * org.sunnycake.aton.dao.LaboratorioDAO#guardarLaboratorio(Laboratorio)
     */
    @Override
    public void guardarLaboratorio(Laboratorio laboratorio) throws ExcepcionConsulta {
        guardarEntidad(laboratorio);
    }

    @Override
    public Laboratorio buscarLaboratorioPorId(int idLaboratorio) throws ExcepcionConsulta {
        return getEntidadPorClave(idLaboratorio);
    }

    @Override
    public void eliminarLaboratorioPorId(int id) throws ExcepcionConsulta {
        eliminarEntidad(buscarLaboratorioPorId(id));
    }

    @Override
    public void actualizarLaboratorio(Laboratorio laboratorio) throws ExcepcionConsulta {
        actualizarEntidad(laboratorio);
    }
}
