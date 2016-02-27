/**
 * Paquete de lógica del negocio.
 */
package org.sunnycake.aton.service;

import java.util.Set;
import org.sunnycake.aton.dto.Equipo;

import org.sunnycake.aton.dto.Orden;
import org.sunnycake.aton.dto.OrdenPK;
import org.sunnycake.aton.dto.UsuarioWeb;

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

    void guardarOrden(Equipo pkEquipo, UsuarioWeb usuarioWeb, String comando, String resultado, int codigoSalida, boolean interrumpir, boolean sudo);

    void actualizarOrden(Orden orden);

    void eliminarOrdenPorClave(OrdenPK ip);

    Set<Orden> buscarTodasLasOrdenes();

    boolean esClaveUnica(OrdenPK ip);

}
