package org.sunnycake.aton.service;

import java.util.List;

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

	void actualizarSala(Sala sala);

	void eliminarSalaPorId(int id);

	List<Sala> buscarTodasLasSalas();

	boolean esIdUnico(int id);

}
