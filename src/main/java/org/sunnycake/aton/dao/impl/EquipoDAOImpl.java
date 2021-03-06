/**
 *
 */
package org.sunnycake.aton.dao.impl;

import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.sunnycake.aton.dao.DAOAbstracto;
import org.sunnycake.aton.dao.EquipoDAO;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * @author camilo
 *
 */
@Repository("equipoDAO")
public class EquipoDAOImpl extends DAOAbstracto<String, Equipo> implements EquipoDAO {

    /**
     * Método que hace uso del DAOAbstracto para que se obtengan todos los
     * elementos de la entidad
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     * @see org.sunnycake.aton.dao.EquipoDAO#obtenerTodos()
     */
    @Override
    public Set<Equipo> obtenerTodos() throws ExcepcionConsulta {
        return getTodos();
    }

    /**
     * Método que utiliza el DAOAbstracto para almacenar nuevos Equipos, no
     * permite que la ip ya exista.
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     * @see org.sunnycake.aton.dao.EquipoDAO#guardarEquipo(Equipo)
     */
    @Override
    public void guardarEquipo(Equipo equipo) throws ExcepcionConsulta {
        guardarEntidad(equipo);
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.dao.EquipoDAO#actualizar(org.sunnycake.dto.Equipo)
     */
    @Override
    public void actualizarEquipo(Equipo equipo) throws ExcepcionConsulta {
        actualizarEntidad(equipo);
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.dao.EquipoDAO#obtener(java.lang.String)
     */
    @Override
    public Equipo buscarEquipoPorIp(String ip) throws ExcepcionConsulta {
        return getEntidadPorClave(ip);
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.dao.EquipoDAO#eliminar(org.sunnycake.dto.Equipo)
     */
    @Override
    public void eliminarEquipoPorIp(String ip) throws ExcepcionConsulta {
        eliminarEntidad(buscarEquipoPorIp(ip));
    }

    @Override
    public Equipo buscarEquipoPorNombre(String nombre) throws ExcepcionConsulta {
        Criteria criteria = crearCriteria();
        criteria.add(Restrictions.eq("NOMBRE", nombre));
        return (Equipo) criteria.uniqueResult();
    }

}
