/**
 * 
 */
package org.sunnycake.aton.service;

import java.util.List;
import java.util.Set;

import org.sunnycake.aton.dto.Orden;
import org.sunnycake.aton.dto.OrdenPK;

/**
 * 
 * Servicio para los objetos de tipo Orden
 * 
 * @author Camilo Sampedro
 *
 */
public interface OrdenService {
	Orden buscarOrdenPorClave(OrdenPK ip);

	void guardarOrden(Orden orden);

	void actualizarOrden(Orden orden);

	void eliminarOrdenPorClave(OrdenPK ip);

	Set<Orden> buscarTodasLasOrdenes();

	boolean esClaveUnica(OrdenPK ip);

}
