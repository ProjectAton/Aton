/**
 * 
 */
package org.sunnycake.aton.service;

import java.util.List;
import java.util.Set;

import org.sunnycake.aton.dto.Equipo;

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

	void actualizarEquipo(Equipo equipo);

	void eliminarEquipoPorIp(String ip);

	Set<Equipo> buscarTodosLosEquipos();

	Equipo buscarEquipoPorNombre(String nombre);

	boolean esIpUnica(String ip);

}
