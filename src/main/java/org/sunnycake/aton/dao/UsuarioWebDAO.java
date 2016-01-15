package org.sunnycake.aton.dao;

import java.util.List;

import org.sunnycake.aton.dto.UsuarioWeb;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * Interfaz para hacer consultas en la base de datos con la tabla UsuarioWeb.
 * 
 * @author cvanessa.perez
 *
 */
public interface UsuarioWebDAO {
	/**
	 * Obtiene una lista con todos los Usuarios registrados en el portal web,
	 * que se encuentran en la base de datos
	 * 
	 * 
	 *
	 */
	public List<UsuarioWeb> obtenerTodos() throws ExcepcionConsulta;

	/**
	 * Registra un Usuario web dentro de la base de datos
	 * 
	 * @throws ExcepcionConsulta
	 */
	public void guardarUsuarioWeb(UsuarioWeb usuario) throws ExcepcionConsulta;

	/**
	 * Busca en la base de datos un usuario web de acuerdo con su clave primaria
	 */
	public UsuarioWeb obtenerUsuarioWebPorUsuario(String usuario) throws ExcepcionConsulta;

	/**
	 * Elimina un usuario web de la base de datos
	 */
	public void eliminarUsuarioWebPorUsuario(String usuario) throws ExcepcionConsulta;

	/**
	 * Reemplaza la información existente de un usuario web en la base de datos,
	 * por uno nuevo con la misma pk
	 */
	public void actualizarUsuarioWeb(UsuarioWeb usuario) throws ExcepcionConsulta;
}
