/**
 * Paquete de lógica del negocio.
 */
package org.sunnycake.aton.service;

import java.util.Set;
import org.joda.time.LocalDate;
import org.sunnycake.aton.dto.Equipo;

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

    void guardarEstado(Equipo pkEquipo, LocalDate pkFecha, String descripcion);

    void actualizarEstado(Estado estado);

    void eliminarEstadoPorIp(String ip);

    Set<Estado> buscarTodosLosEstados();

    Estado buscarEstadoPorNombre(String nombre);

    boolean esIpUnica(String ip);

}
