package org.sunnycake.aton.dao;

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
     * @return Set de sugerencias (Sin repetidos)
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    public Set<Sugerencia> obtenerTodos() throws ExcepcionConsulta;

    /**
     * Registra una sugerencia dentro de la base de datos
     *
     * @param sugerencia Sugerencia a guardar.
     * @throws ExcepcionConsulta
     */
    public void guardarSugerencia(Sugerencia sugerencia) throws ExcepcionConsulta;

    /**
     * Busca en la base de datos una sugerencia de acuerdo con su clave primaria
     *
     * @param id Identificador de la sugerencia a buscar.
     * @return Sugerencia encontrada o nulo en caso de no encontrarse-
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    public Sugerencia obtenerSugerenciaPorClave(long id) throws ExcepcionConsulta;

    /**
     * Elimina una sugerencia de la base de datos
     *
     * @param id Identificador de la sugerencia.
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    public void eliminarSugerenciaPorClave(long id) throws ExcepcionConsulta;

    /**
     * Reemplaza la información existente de una sugerencia en la base de datos,
     * por una nueva con la misma pk
     *
     * @param sugerencia Sugerencia con los datos nuevos.
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    public void actualizarSugerencia(Sugerencia sugerencia) throws ExcepcionConsulta;
}
