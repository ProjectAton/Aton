package org.sunnycake.aton.form;

import java.util.List;

import org.sunnycake.aton.dto.Equipo;

public class SeleccionEquipos {
	private List<Equipo> equipos;

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}
	
	@Override
	public String toString(){
		String string = "";
		for (Equipo equipo: equipos){
			string += equipo;
		}
		return string;
	}
	
	
}
