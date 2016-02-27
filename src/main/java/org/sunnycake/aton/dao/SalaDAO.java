package org.sunnycake.aton.dao;

import java.util.Set;

import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * Interfaz para hacer consultas en la base de datos con la tabla Sala.
 *
 * @author Vanesa Perez
 *
 */
public interface SalaDAO {

    /**
     * Obtiene una lista con todas las salas que se encuentran en la base de
     * datos
     *
     * @author cvanessa.perez
     * @return Set de salas (Sin repetidos)
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     *
     */
    public Set<Sala> obtenerTodos() throws ExcepcionConsulta;

    /**
     * Registra una sala dentro de la base de datos
     *
     * @param sala Sala a guardar
     * @throws ExcepcionConsulta Error en la consulta
     */
    public void guardarSala(Sala sala) throws ExcepcionConsulta;

    /**
     * Busca en la base de datos una sala de acuerdo con su clave primaria Id
     *
     * @param idSala Identificador de la sala.
     * @return Sala encontrada o nulo en caso de no encontrarla
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    public Sala buscarSalaPorId(int idSala) throws ExcepcionConsulta;

    /**
     * Elimina una sala de la base de datos
     *
     * @param id Identidicador de la sala
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    public void eliminarSalaPorId(int id) throws ExcepcionConsulta;

    /**
     * Reemplaza la información de la orden con igual idenficador MAC en la base
     * de datos por la que es ingresada como parámetro 'orden'
     *
     * @param sala Sala con la información nueva
     * @throws ExcepcionConsulta Error en la consulta
     */
    public void actualizar(Sala sala) throws ExcepcionConsulta;
}
