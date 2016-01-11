package org.sunnycake.aton.dao;

import java.util.List;

import org.sunnycake.aton.dto.UsuarioWeb;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * Interfaz para hacer consultas en la base de datos con la tabla UsuarioWeb.
 * @author camilo
 *
 */
public interface UsuarioWebDAO {
	/**
	 * Obtiene una lista con todos los Usuarios registrados en el portal web,
	 * que se encuentran en la base de datos
	 * 
	 * @author cvanessa.perez
	 *
	 */
	public List<UsuarioWeb> obtenerTodos() throws ExcepcionConsulta;

	/**
	 * Registra un Usuario web dentro de la base de datos
	 * 
	 * @throws ExcepcionConsulta
	 */
	public void guardar(UsuarioWeb usuario) throws ExcepcionConsulta;

	/**
	 * Busca en la base de datos un usuario web de acuerdo con su clave primaria
	 */
	public UsuarioWeb obtener(String usuario) throws ExcepcionConsulta;

	/**
	 * Elimina un usuario web de la base de datos
	 */
	public void eliminar(UsuarioWeb usuario) throws ExcepcionConsulta;

	/**
	 * Reemplaza la información existente de un usuario web en la base de datos,
	 * por uno nuevo con la misma pk
	 */
	public void actualizar(UsuarioWeb usuario) throws ExcepcionConsulta;
}
