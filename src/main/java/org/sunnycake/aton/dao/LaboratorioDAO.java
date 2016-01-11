package org.sunnycake.aton.dao;

import org.sunnycake.aton.dto.Laboratorio;
import org.sunnycake.aton.exception.ExcepcionConsulta;

import java.util.List;

/**
 * Interfaz para hacer consultas en la base de datos con la tabla Laboratorio.
 * 
 * @author Vanesa Perez
 *
 */
public interface LaboratorioDAO {
	/**
	 * Obtiene una lista con todos los laboratorios en la base de datos.
	 * 
	 * @return Lista con todos los Laboratorios.
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public List<Laboratorio> obtenerTodos() throws ExcepcionConsulta;

	/**
	 * Registra un laboratorio dentro de la base de datos.
	 * 
	 * @param laboratorio
	 *            Laboratorio a registrar
	 * @throws ExcepcionContulta
	 *             Error en la consulta
	 */
	public void guardar(Laboratorio laboratorio) throws ExcepcionConsulta;

	/**
	 * Reemplaza la información del laboratorio con igual idenficador en la base
	 * de datos por la que es ingresada como parámetro 'Laboratorio'
	 * 
	 * @param Laboratorio
	 *            Laboratorio con la información nueva
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public void actualizar(Laboratorio laboratorio) throws ExcepcionConsulta;

	/**
	 * Busca en la base de datos un laboratorio con la misma clave id
	 * 
	 * @param id
	 *            Identificador del laboratorio a buscar
	 * @return laboratorio encontrado
	 * @throws ExcepcionContulta
	 *             Error en la consulta
	 */
	public Laboratorio obtener(long id) throws ExcepcionConsulta;

	/**
	 * Elimina de la base de datos el laboratorio con el mismo identificador
	 * 
	 * @param Laboratorio
	 *            Laboratorio a eliminar en la base de datos
	 * @throws ExcepcionConsulta
	 *             Error en la consulta
	 */
	public void eliminar(Laboratorio Laboratorio) throws ExcepcionConsulta;
}
