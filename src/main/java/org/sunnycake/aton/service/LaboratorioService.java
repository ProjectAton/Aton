package org.sunnycake.aton.service;

import java.util.Set;

import org.sunnycake.aton.dto.Laboratorio;

public interface LaboratorioService {
	Laboratorio buscarLaboratorioPorId(long id);

	void guardarLaboratorio(Laboratorio laboratorio);

	void actualizarLaboratorio(Laboratorio laboratorio);

	void eliminarLaboratorioPorId(long id);

	Set<Laboratorio> buscarTodosLosLaboratorios();

	boolean esIdUnico(long id);
}
