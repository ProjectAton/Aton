package org.sunnycake.aton.dao;

import java.util.List;

import org.sunnycake.aton.dto.Orden;
import org.sunnycake.aton.dto.OrdenPK;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * Interfaz para hacer consultas en la base de datos con la tabla Orden.
 * 
 * @author camilo
 *
 */
public interface OrdenDAO {
	/**
	 * Obtiene una lista con todas las ordenes en la base de datos.
	 * 
	 * @return Lista con todos las órdenes.
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public List<Orden> obtenerTodos() throws ExcepcionConsulta;

	/**
	 * Registra una orden dentro de la base de datos.
	 * 
	 * @param orden
	 *            Orden a registrar
	 * @throws ExcepcionContulta
	 *             Error en la consulta
	 */
	public void guardar(Orden orden) throws ExcepcionConsulta;

	/**
	 * Reemplaza la información de la orden con igual idenficador MAC en la base
	 * de datos por la que es ingresada como parámetro 'orden'
	 * 
	 * @param orden
	 *            Orden con la información nueva
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public void actualizar(Orden orden) throws ExcepcionConsulta;

	/**
	 * Busca en la base de datos un Orden con la misma clave fecha + sesion
	 * 
	 * @param clave
	 *            Clave compuesta fecha + sesion para la búsqueda
	 * @return Orden encontrado
	 * @throws ExcepcionContulta
	 *             Error en la consulta
	 */
	public Orden obtener(OrdenPK clave) throws ExcepcionConsulta;

	/**
	 * Elimina de la base de datos el Orden con el mismo identificador fecha +
	 * sesion
	 * 
	 * @param Orden
	 *            Orden a eliminar en la base de datos
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public void eliminar(Orden Orden) throws ExcepcionConsulta;
}
