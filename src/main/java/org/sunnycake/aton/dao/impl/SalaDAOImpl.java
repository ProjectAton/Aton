/**
 * 
 */
package org.sunnycake.aton.dao.impl;

import java.util.List;

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

	public List<Sala> obtenerTodos() throws ExcepcionConsulta {
		return getAll();
	}

	public void guardarSala(Sala sala) throws ExcepcionConsulta {
		persist(sala);
	}

	public Sala buscarSalaPorId(int idSala) throws ExcepcionConsulta {
		return getByKey(idSala);
	}

	public void eliminarSalaPorId(int id) throws ExcepcionConsulta {
		delete(buscarSalaPorId(id));
	}

	public void actualizar(Sala sala) throws ExcepcionConsulta {
		update(sala);
	}

}
