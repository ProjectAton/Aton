/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sunnycake.aton.configuration;

import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author camilo
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ConfiguracionAplicacion.class);
        context.register(ConfiguracionHibernate.class);
        context.refresh();
        

//        final FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
//        characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
//        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
//        characterEncodingFilter.setInitParameter("forceEncoding", "true");
//
//        final FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
//        springSecurityFilterChain.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        servletContext.addListener(new ContextLoaderListener(context));

        // Reemplazo de: (SpringServlet cambió por ServletContainer
        //      <servlet-name>SpringWSAton</servlet-name>
        //      <servlet-class>com.sun.jersey.spi.spring.container.servlet.SpringServlet</servlet-class>
        //  	<init-param>
        //  		<param-name>com.sun.jersey.config.property.packages</param-name>
        //  		<param-value>org.sunnycake.aton.ws</param-value>
        //  	</init-param>
        //  	<load-on-startup>1</load-on-startup>
        final AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(ConfiguracionAplicacion.class);
        dispatcherContext.refresh();
        final ServletRegistration.Dynamic appServlet = servletContext.addServlet("SpringWSAton", new DispatcherServlet(dispatcherContext));
        appServlet.setInitParameter("com.sun.jersey.config.property.packages", "org.sunnycake.aton.ws");
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");

        final Set<String> mappingConflicts = appServlet.addMapping("/api/*");

        if (!mappingConflicts.isEmpty()) {
            throw new IllegalStateException("'appServlet' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
        }
    }
}
