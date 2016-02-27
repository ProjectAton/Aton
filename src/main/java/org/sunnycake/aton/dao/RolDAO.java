package org.sunnycake.aton.dao;

import java.util.Set;

import org.sunnycake.aton.dto.Rol;
import org.sunnycake.aton.exception.ExcepcionConsulta;

/**
 * Interfaz para hacer consultas en la base de datos con la tabla TipoUsuario.
 *
 * @author cvanessa.perez
 *
 */
public interface RolDAO {

    /**
     * Obtiene una lista con todos los tipo de usuarios que han hecho los
     * usuarios que se encuentran en la base de datos
     *
     *
     * @return Set de roles (Sin repetidos) en la bae de datos
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    public Set<Rol> obtenerTodos() throws ExcepcionConsulta;

    /**
     * Registra un Tipo de usuario (Administrador, cliente, supervisor) dentro
     * de la base de datos
     *
     * @param tipo Rol a guardar
     * @throws ExcepcionConsulta
     */
    public void guardar(Rol tipo) throws ExcepcionConsulta;

    /**
     * Busca en la base de datos un tipo de usuario de acuerdo con su clave
     * primaria
     *
     * @param id Id a buscar en la base de datos
     * @return Rol encontrado o nulo si no se encuentra
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    public Rol obtener(int id) throws ExcepcionConsulta;

    /**
     * Elimina un tipo de usuario de la base de datos
     *
     * @param id Id del rol a eliminar
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    public void eliminar(int id) throws ExcepcionConsulta;

    /**
     * Reemplaza la información existente de un tipo de usuario en la base de
     * datos, por una nueva con la misma pk
     *
     * @param tipo Rol a actualizar
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    public void actualizar(Rol tipo) throws ExcepcionConsulta;
}
