/**
 * 
 */
package org.sunnycake.aton.service;

import java.util.List;
import java.util.Set;

import org.sunnycake.aton.dto.Estado;

/**
 * 
 * Servicio para los objetos de tipo Estado
 * 
 * @author Camilo Sampedro
 *
 */
public interface EstadoService {
	Estado buscarEstadoPorIp(String ip);

	void guardarEstado(Estado estado);

	void actualizarEstado(Estado estado);

	void eliminarEstadoPorIp(String ip);

	Set<Estado> buscarTodosLosEstados();

	Estado buscarEstadoPorNombre(String nombre);

	boolean esIpUnica(String ip);

}
