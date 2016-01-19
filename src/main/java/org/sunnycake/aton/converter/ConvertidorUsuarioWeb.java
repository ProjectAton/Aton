package org.sunnycake.aton.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.sunnycake.aton.dto.UsuarioWeb;
import org.sunnycake.aton.service.UsuarioWebService;

@Component
public class ConvertidorUsuarioWeb implements Converter<Object, UsuarioWeb> {

	@Autowired
	UsuarioWebService usuarioWebService;

	private Logger logger = LogManager.getLogger(ConvertidorUsuarioWeb.class.getName());

	@Override
	public UsuarioWeb convert(Object arg0) {
		logger.debug("Convirtiendo " + arg0.toString());
		String usuario = arg0.toString();
		UsuarioWeb usuarioWeb = usuarioWebService.obtenerUsuarioWebPorUsuario(usuario);
		return usuarioWeb;
	}
}
