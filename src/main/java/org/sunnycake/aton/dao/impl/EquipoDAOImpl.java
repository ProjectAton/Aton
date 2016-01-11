/**
 * 
 */
package org.sunnycake.aton.dao.impl;

import java.util.List;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.dao.EquipoDAO#obtenerTodos()
	 */
	public List<Equipo> obtenerTodos() throws ExcepcionConsulta {
		// TODO Auto-generated method stub
		return getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.dao.EquipoDAO#guardar(org.sunnycake.dto.Equipo)
	 */
	public void guardarEquipo(Equipo equipo) throws ExcepcionConsulta {
		persist(equipo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.dao.EquipoDAO#actualizar(org.sunnycake.dto.Equipo)
	 */
	public void actualizarEquipo(Equipo equipo) throws ExcepcionConsulta {
		update(equipo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.dao.EquipoDAO#obtener(java.lang.String)
	 */
	public Equipo buscarEquipoPorMac(String mac) throws ExcepcionConsulta {
		return getByKey(mac);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunnycake.dao.EquipoDAO#eliminar(org.sunnycake.dto.Equipo)
	 */
	public void eliminarEquipoPorMac(String mac) throws ExcepcionConsulta {
		delete(buscarEquipoPorMac(mac));

	}

	public Equipo buscarEquipoPorIp(String ip) throws ExcepcionConsulta {
		Criteria criteria = crearCriteria();
		criteria.add(Restrictions.eq("IP", ip));
		return (Equipo) criteria.uniqueResult();
	}
	
	

}