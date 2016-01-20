package org.sunnycake.aton.dao;

import java.util.List;
import java.util.Set;

import org.sunnycake.aton.dto.Sesion;
import org.sunnycake.aton.dto.SesionPK;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * Interfaz para hacer consultas en la base de datos con la tabla Sesion.
 * @author Camilo Sampedro
 *
 */
public interface SesionDAO {
	/**
	 * Obtiene una lista con todas las sesiones que han hecho los usuarios y que
	 * se encuentran en la base de datos
	 * 
	 * @author cvanessa.perez
	 *
	 */
	public Set<Sesion> obtenerTodos() throws ExcepcionConsulta;

	/**
	 * Registra una sesion dentro de la base de datos
	 * 
	 * @throws ExcepcionConsulta
	 */
	public void guardarSesion(Sesion sesion) throws ExcepcionConsulta;

	/**
	 * Busca en la base de datos una sesion de acuerdo con su clave primaria
	 */
	public Sesion obtenerSesionPorClave(SesionPK claveP) throws ExcepcionConsulta;

	/**
	 * Elimina una sesion de la base de datos
	 */
	public void eliminarSesionPorClave(SesionPK sesion) throws ExcepcionConsulta;
	/**
	 * Reemplaza la información existente de una sesion en la base de datos,
	 * por una nueva con la misma pk
	 */
	public void actualizarSesion(Sesion sesion) throws ExcepcionConsulta;
}
