/**
 * Paquete de lógica del negocio.
 */
package org.sunnycake.aton.service;

import java.util.Set;
import org.sunnycake.aton.dto.Laboratorio;

import org.sunnycake.aton.dto.Sala;

/**
 *
 * Servicio para los objetos de tipo Sala
 *
 * @author camilo
 *
 */
public interface SalaService {

    Sala buscarSalaPorId(int id);

    void guardarSala(Sala sala);

    void guardarSala(int id, String nombre, Laboratorio laboratorio, String mediosAudiovisuales, String enseres);

    void actualizarSala(Sala sala);

    void eliminarSalaPorId(int id);

    Set<Sala> buscarTodasLasSalas();

    boolean esIdUnico(int id);

}
