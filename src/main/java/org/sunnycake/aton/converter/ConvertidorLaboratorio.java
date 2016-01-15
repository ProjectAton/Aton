package org.sunnycake.aton.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.sunnycake.aton.dto.Laboratorio;
import org.sunnycake.aton.service.LaboratorioService;

@Component
public class ConvertidorLaboratorio implements Converter<Object, Laboratorio>{
	
	@Autowired
    LaboratorioService laboratorioService;
	
	private Logger logger = LogManager.getLogger(ConvertidorLaboratorio.class.getName());
 
    /**
     * Obtiene una Sala por Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Laboratorio convert(Object element) {
    	logger.debug("Convirtiendo: " + element);
        Integer id = Integer.parseInt((String)element);
        Laboratorio laboratorio= laboratorioService.buscarLaboratorioPorId(id);
        return laboratorio;
    }
}