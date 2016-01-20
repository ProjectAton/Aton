package org.sunnycake.aton.dao;

import java.util.List;
import java.util.Set;

import org.sunnycake.aton.dto.Sugerencia;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * Interfaz para hacer consultas en la base de datos con la tabla Sugerencia.
 * 
 * @author cvanessa.perez
 *
 */
public interface SugerenciaDAO {
	/**
	 * Obtiene una lista con todas las sugerencias que han hecho los usuarios y
	 * que se encuentran en la base de datos
	 * 
	 * 
	 *
	 */
	public Set<Sugerencia> obtenerTodos() throws ExcepcionConsulta;

	/**
	 * Registra una sugerencia dentro de la base de datos
	 * 
	 * @throws ExcepcionConsulta
	 */
	public void guardarSugerencia(Sugerencia sugerencia) throws ExcepcionConsulta;

	/**
	 * Busca en la base de datos una sugerencia de acuerdo con su clave primaria
	 */
	public Sugerencia obtenerSugerenciaPorClave(long id) throws ExcepcionConsulta;

	/**
	 * Elimina una sugerencia de la base de datos
	 */
	public void eliminarSugerenciaPorClave(long id) throws ExcepcionConsulta;

	/**
	 * Reemplaza la información existente de una sugerencia en la base de datos,
	 * por una nueva con la misma pk
	 */
	public void actualizarSugerencia(Sugerencia sugerencia) throws ExcepcionConsulta;
}
