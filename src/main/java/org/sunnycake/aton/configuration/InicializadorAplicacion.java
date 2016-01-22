package org.sunnycake.aton.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Inicializa las configuraciones generales web.
 * 
 * @author Camilo Sampedro
 *
 */
public class InicializadorAplicacion extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ConfiguracionAplicacion.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
