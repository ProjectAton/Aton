/**
 * 
 */
package org.sunnycake.aton.service;

import java.util.List;

import org.sunnycake.aton.dto.Equipo;

/**
 * 
 * Servicio para los objetos de tipo Equipo
 * 
 * @author Camilo Sampedro
 *
 */
public interface EquipoService {
	Equipo buscarEquipoPorMac(String mac);

	void guardarEquipo(Equipo equipo);

	void actualizarEquipo(Equipo equipo);

	void eliminarEquipoPorMac(String mac);

	List<Equipo> buscarTodosLosEquipos();

	Equipo buscarEquipoPorIp(String ip);

	boolean esMacUnica(String mac);

}
