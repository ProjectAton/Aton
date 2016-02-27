/**
 * Paquete de lógica del negocio.
 */
package org.sunnycake.aton.service;

import java.util.Set;

import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Sala;

/**
 *
 * Servicio para los objetos de tipo Equipo
 *
 * @author Camilo Sampedro
 *
 */
public interface EquipoService {

    Equipo buscarEquipoPorIp(String ip);

    void guardarEquipo(Equipo equipo);

    void guardarEquipo(String mac, String nombre, String usuario, String password, String ip, Sala sala, String descripcion, String mensaje, boolean seleccionado);

    void guardarEquipo(String usuario, String password, String ip);

    void actualizarEquipo(Equipo equipo);

    void eliminarEquipoPorIp(String ip);

    Set<Equipo> buscarTodosLosEquipos();

    Equipo buscarEquipoPorNombre(String nombre);

    boolean esIpUnica(String ip);

}
