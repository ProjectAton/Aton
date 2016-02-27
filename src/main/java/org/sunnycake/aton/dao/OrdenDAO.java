package org.sunnycake.aton.dao;

import java.util.Set;

import org.sunnycake.aton.dto.Orden;
import org.sunnycake.aton.dto.OrdenPK;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * DAO para la tabla Orden.
 *
 * @author Camilo Sampedro
 *
 */
public interface OrdenDAO {

    /**
     * Obtiene una lista con todas las ordenes en la base de datos.
     *
     * @return Lista con todos las órdenes.
     * @throws ExcepcionConsulta Error en la consulta
     */
    public Set<Orden> obtenerTodos() throws ExcepcionConsulta;

    /**
     * Registra una orden dentro de la base de datos.
     *
     * @param orden Orden a registrar
     * @throws ExcepcionConsulta Error en la consulta
     */
    public void guardarOrden(Orden orden) throws ExcepcionConsulta;

    /**
     * Reemplaza la información de la orden con igual idenficador MAC en la base
     * de datos por la que es ingresada como parámetro 'orden'
     *
     * @param orden Orden con la información nueva
     * @throws ExcepcionConsulta Error en la consulta
     */
    public void actualizarOrden(Orden orden) throws ExcepcionConsulta;

    /**
     * Busca en la base de datos un Orden con la misma clave fecha + sesion
     *
     * @param clave Clave compuesta fecha + sesion para la búsqueda
     * @return Orden encontrado
     * @throws ExcepcionConsulta Error en la consulta
     */
    public Orden obtenerOrdenPorClave(OrdenPK clave) throws ExcepcionConsulta;

    /**
     * Elimina de la base de datos el Orden con el mismo identificador fecha +
     * sesion
     *
     * @param orden Orden a eliminar en la base de datos
     * @throws ExcepcionConsulta Error en la consulta
     */
    public void eliminarOrdenPorClave(OrdenPK orden) throws ExcepcionConsulta;
}
