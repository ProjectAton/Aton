/**
 * 
 */
package org.sunnycake.aton.service;

import java.util.List;
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

	void actualizarUsuarioWeb(UsuarioWeb usuario);

	void eliminarUsuarioWebPorUsuario(String usuario);

	Set<UsuarioWeb> buscarTodosLosUsuariosWeb();

	boolean esUsuarioUnico(String usuario);

}
