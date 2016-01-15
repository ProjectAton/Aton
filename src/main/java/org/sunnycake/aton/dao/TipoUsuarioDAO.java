package org.sunnycake.aton.dao;

import java.util.List;

import org.sunnycake.aton.dto.TipoUsuario;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * Interfaz para hacer consultas en la base de datos con la tabla TipoUsuario.
 * 
 * @author cvanessa.perez
 *
 */
public interface TipoUsuarioDAO {
	/**
	 * Obtiene una lista con todos los tipo de usuarios que han hecho los
	 * usuarios que se encuentran en la base de datos
	 * 
	 *
	 */
	public List<TipoUsuario> obtenerTodos() throws ExcepcionConsulta;

	/**
	 * Registra un Tipo de usuario (Administrador, cliente, supervisor) dentro
	 * de la base de datos
	 * 
	 * @throws ExcepcionConsulta
	 */
	public void guardarTipoUsuario(TipoUsuario tipo) throws ExcepcionConsulta;

	/**
	 * Busca en la base de datos un tipo de usuario de acuerdo con su clave
	 * primaria
	 */
	public TipoUsuario obtenerTipoUsuarioPorId(int id) throws ExcepcionConsulta;

	/**
	 * Elimina un tipo de usuario de la base de datos
	 */
	public void eliminarTipoUsuarioPorId(int id) throws ExcepcionConsulta;

	/**
	 * Reemplaza la información existente de un tipo de usuario en la base de
	 * datos, por una nueva con la misma pk
	 */
	public void actualizarTipoUsuario(TipoUsuario tipo) throws ExcepcionConsulta;
}
