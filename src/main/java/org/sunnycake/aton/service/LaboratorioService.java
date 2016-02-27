/**
 * Paquete de lógica del negocio.
 */
package org.sunnycake.aton.service;

import java.util.Set;

import org.sunnycake.aton.dto.Laboratorio;

public interface LaboratorioService {

    Laboratorio buscarLaboratorioPorId(int id);

    void guardarLaboratorio(Laboratorio laboratorio);

    void guardarLaboratorio(int id, String ubicacion, String administracion, String nombre);

    void actualizarLaboratorio(Laboratorio laboratorio);

    void eliminarLaboratorioPorId(int id);

    Set<Laboratorio> buscarTodosLosLaboratorios();

    boolean esIdUnico(int id);
}
