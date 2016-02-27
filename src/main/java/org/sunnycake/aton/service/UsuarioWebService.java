/**
 * Paquete de lógica del negocio.
 */
package org.sunnycake.aton.service;

import java.util.Set;

import org.sunnycake.aton.dto.UsuarioWeb;

/**
 *
 * Servicio para los objetos de tipo UsuarioWeb
 *
 * @author Camilo Sampedro
 *
 */
public interface UsuarioWebService {

    UsuarioWeb obtenerUsuarioWebPorUsuario(String usuario);

    void guardarUsuarioWeb(UsuarioWeb usuario);

    void guardarUsuarioWeb(String usuario, String password, boolean enabled);

    void actualizarUsuarioWeb(UsuarioWeb usuario);

    void eliminarUsuarioWebPorUsuario(String usuario);

    Set<UsuarioWeb> buscarTodosLosUsuariosWeb();

    boolean esUsuarioUnico(String usuario);

}
