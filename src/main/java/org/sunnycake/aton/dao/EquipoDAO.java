package org.sunnycake.aton.dao;

import java.util.List;

import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * 
 * @author camilo
 *
 */
public interface EquipoDAO {
	/**
	 * Obtiene una lista con todos los equipos en la base de datos.
	 * 
	 * @return Lista con todos los equipos.
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public List<Equipo> obtenerTodos() throws ExcepcionConsulta;

	/**
	 * Registra un equipo dentro de la base de datos.
	 * 
	 * @param equipo
	 *            Equipo a registrar
	 * @throws ExcepcionContulta
	 *             Error en la consulta
	 */
	public void guardarEquipo(Equipo equipo) throws ExcepcionConsulta;

	/**
	 * Reemplaza la información del equipo con igual idenficador MAC en la base
	 * de datos por la que es ingresada como parámetro 'equipo'
	 * 
	 * @param equipo
	 *            Equipo con la información nueva
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public void actualizarEquipo(Equipo equipo) throws ExcepcionConsulta;

	/**
	 * Busca en la base de datos un equipo con la clave dirección MAC igual a la
	 * especificada en el parámetro MAC.
	 * 
	 * @param mac
	 *            Identificador del equipo
	 * @return Equipo encontrado
	 * @throws ExcepcionContulta
	 *             Error en la consulta
	 */
	public Equipo buscarEquipoPorMac(String mac) throws ExcepcionConsulta;

	/**
	 * Elimina de la base de datos el equipo con el mismo identificador que
	 * 'equipo' (Misma dirección MAC)
	 * 
	 * @param equipo
	 *            Equipo a eliminar en la base de datos
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public void eliminarEquipoPorMac(String mac) throws ExcepcionConsulta;
	
	/**
	 * 
	 * @param ip
	 * @return
	 * @throws ExcepcionConsulta
	 */
	public Equipo buscarEquipoPorIp(String ip) throws ExcepcionConsulta;
}
