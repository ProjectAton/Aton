package org.sunnycake.aton.dao;

import java.util.List;

import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * Interfaz para hacer consultas en la base de datos con la tabla Sala.
 * 
 * @author Vanesa Perez
 *
 */
public interface SalaDAO {
	/**
	 * Obtiene una lista con todas las salas que se encuentran en la base de
	 * datos
	 * 
	 * @author cvanessa.perez
	 *
	 */
	public List<Sala> obtenerTodos() throws ExcepcionConsulta;

	/**
	 * Registra una sala dentro de la base de datos
	 * 
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public void guardarSala(Sala sala) throws ExcepcionConsulta;

	/**
	 * Busca en la base de datos una sala de acuerdo con su clave primaria Id
	 */
	public Sala buscarSalaPorId(int idSala) throws ExcepcionConsulta;

	/**
	 * Elimina una sala de la base de datos
	 */
	public void eliminarSalaPorId(int id) throws ExcepcionConsulta;

	/**
	 * Reemplaza la información de la orden con igual idenficador MAC en la base
	 * de datos por la que es ingresada como parámetro 'orden'
	 * 
	 * @param sala
	 *            Sala con la información nueva
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public void actualizar(Sala sala) throws ExcepcionConsulta;
}
