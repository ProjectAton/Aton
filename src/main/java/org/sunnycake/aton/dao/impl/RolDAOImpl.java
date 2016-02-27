/**
 *
 */
package org.sunnycake.aton.dao.impl;

import java.util.Set;

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

    /**
     * Método que hace uso del DAOAbstracto para que se obtengan todos los
     * elementos de la entidad
     *
     * @see org.sunnycake.aton.dao.RolDAO.obtenerTodos()
     */
    @Override
    public Set<Rol> obtenerTodos() throws ExcepcionConsulta {
        return getTodos();
    }

    /**
     * Método que utiliza el DAOAbstracto para almacenar nuevos Roles, no
     * permite que el id ya exista.
     *
     * @see org.sunnycake.aton.dao.RolDAO#guardar(Rol)
     */
    @Override
    public void guardar(Rol tipo) throws ExcepcionConsulta {
        guardarEntidad(tipo);
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.TipoUsuarioDAO#obtenerTipoUsuarioPorId(int)
     */
    @Override
    public Rol obtener(int id) throws ExcepcionConsulta {
        // TODO Auto-generated method stub
        return getEntidadPorClave(id);
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.aton.dao.TipoUsuarioDAO#eliminarTipoUsuarioPorId(int)
     */
    @Override
    public void eliminar(int id) throws ExcepcionConsulta {
        // TODO Auto-generated method stub
        eliminarEntidad(obtener(id));
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
        actualizarEntidad(tipo);
    }

}
