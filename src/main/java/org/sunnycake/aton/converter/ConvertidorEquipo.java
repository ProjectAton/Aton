package org.sunnycake.aton.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.service.EquipoService;

@Component
public class ConvertidorEquipo implements Converter<Object, Equipo> {

	@Autowired
	EquipoService equipoService;

	private Logger logger = LogManager.getLogger(ConvertidorEquipo.class.getName());

	@Override
	public Equipo convert(Object arg0) {
		logger.debug("Convirtiendo: " + arg0);
		String ip = arg0.toString();

		return equipoService.buscarEquipoPorIp(ip);
	}

}
