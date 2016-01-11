package org.sunnycake.aton.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.service.SalaService;

@Component
public class ConvertidorSala implements Converter<Object, Sala>{
	
	@Autowired
    SalaService salaService;
	
	private Logger logger = LogManager.getLogger(ConvertidorSala.class.getName());
 
    /**
     * Obtiene una Sala por Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Sala convert(Object element) {
    	logger.debug("Convirtiendo: " + element);
        Integer id = Integer.parseInt((String)element);
        Sala profile= salaService.buscarSalaPorId(id);
        return profile;
    }
}
