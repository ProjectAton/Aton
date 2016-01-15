package org.sunnycake.aton.service;

import java.util.List;

import org.sunnycake.aton.dto.Laboratorio;

public interface LaboratorioService {
	Laboratorio buscarLaboratorioPorId(int id);

	void guardarLaboratorio(Laboratorio laboratorio);

	void actualizarLaboratorio(Laboratorio laboratorio);

	void eliminarLaboratorioPorId(int id);

	List<Laboratorio> buscarTodosLosLaboratorios();

	boolean esIdUnico(long id);
}
