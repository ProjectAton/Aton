package org.sunnycake.aton.dao;

import java.util.List;

import org.sunnycake.aton.dto.Estado;
import org.sunnycake.aton.dto.EstadoPK;
import org.sunnycake.aton.exception.ExcepcionConsulta;


/**
 * Interfaz para hacer consultas en la base de datos con la tabla Estado
 * 
 * @author Vanessa Perez
 *
 */
public interface EstadoDAO {
	/**
	 * Obtiene una lista con todos los estados en la base de datos.
	 * 
	 * @return Lista con todos los Estados.
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public List<Estado> obtenerTodos() throws ExcepcionConsulta;

	/**
	 * Registra un Estado dentro de la base de datos.
	 * 
	 * @param Estado
	 *            Estado a registrar
	 * @throws ExcepcionContulta
	 *             Error en la consulta
	 */
	public void guardar(Estado estado) throws ExcepcionConsulta;

	/**
	 * Reemplaza la información del Estado con igual idenficador MAC en la base
	 * de datos por la que es ingresada como parámetro 'estado'
	 * 
	 * @param Estado
	 *            Estado con la información nueva
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public void actualizar(Estado estado) throws ExcepcionConsulta;

	/**
	 * Busca en la base de datos un estado con la misma clave equipo (Clave
	 * primaria mac) + fecha
	 * 
	 * @param clave
	 *            Clave compuesta equipo + fecha para la búsqueda
	 * @return Estado encontrado
	 * @throws ExcepcionContulta
	 */
	public Estado obtener(EstadoPK clave) throws ExcepcionConsulta;

	/**
	 * Elimina de la base de datos el Estado con el mismo identificador equipo
	 * (Clave primaria mac) + fecha
	 * 
	 * @param Estado
	 *            Estado a eliminar en la base de datos
	 * @throws ExcepcionConsulta
	 */
	public void eliminar(Estado Estado) throws ExcepcionConsulta;
}
