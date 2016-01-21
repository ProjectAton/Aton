/**
 *
 */
package org.sunnycake.aton.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.sunnycake.aton.converter.ConvertidorEquipo;
import org.sunnycake.aton.converter.ConvertidorLaboratorio;
import org.sunnycake.aton.converter.ConvertidorSala;
import org.sunnycake.aton.converter.ConvertidorUsuarioWeb;

/**
 * @author Camilo Sampedro
 *
 */
// Especifica que habrán métodos "bean" anotados con @Bean que podrán ser
// manejados por el contenedor Spring
@Configuration
// Equivalente a mvc:annotation-driven en el XML
@EnableWebMvc
// Equivalente a context:component-scan base-package="..." en el xml, indica
// dónde buscar los beans controlados por Spring
@ComponentScan(basePackages = "org.sunnycake.aton")
public class ConfiguracionAplicacion extends WebMvcConfigurerAdapter {

    private final Logger logger = LogManager.getLogger(ConfiguracionAplicacion.class);

    /**
     * Objeto utilizado para obtener a través del id de una sala (Por ejemplo en
     * un formulario), el enlace a una sala.
     */
    @Autowired
    ConvertidorSala convertidorSala;

    /**
     * Objeto utilizado para obtener a través del id de un laboratorio (Por
     * ejemplo en un formulario), el enlace a un laboratorio.
     */
    @Autowired
    ConvertidorLaboratorio convertidorLaboratorio;

    /**
     * Objeto utilizado para obtener a través de la ip de un equipo (Por ejemplo
     * en un formulario), el enlace a un equipo.
     */
    @Autowired
    ConvertidorEquipo convertidorEquipo;

    /**
     * Objeto utilizado para obtener a través del usuario (Por ejemplo en un
     * formulario), el enlace a un UsuarioWeb.
     */
    @Autowired
    ConvertidorUsuarioWeb convertidorUsuarioWeb;

    /**
     * Configuración de dónde están las vistas del proyecto.
     *
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/vistas/");
        viewResolver.setSuffix(".jsp");
        logger.debug("ViewResolver creado");
        return viewResolver;
    }

    /**
     * Configuración de los mensajes (Errores). (Validaciones)
     *
     * @return Objeto con las configuraciones
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // messages.properties
        messageSource.setBasename("mensajes");
        logger.debug("Mensajero MessageSource creado");
        return messageSource;
    }

    /**
     * Recursos (Javascript, CSS, ...)
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    /**
     * Configurar los convertidores a ser usados Por ejemplo, se necesita
     * convertir el id de una sala, a un objeto Sala en el campo de registro de
     * nuevo equipo
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Integer ID -> Sala sala
        registry.addConverter(convertidorSala);
        registry.addConverter(convertidorLaboratorio);
        registry.addConverter(convertidorEquipo);
        registry.addConverter(convertidorUsuarioWeb);
    }

    /**
     * Optional. It's only required when handling '.' in \@PathVariables which
     * otherwise ignore everything after last '.' in
     *
     * @param matcher
     * @PathVaidables argument. It's a known bug in Spring
     * [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
     * This is a workaround for this issue.
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }
}
