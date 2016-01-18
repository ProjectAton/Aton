package org.sunnycake.aton.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Especifica que habrán métodos "bean" anotados con @Bean que podrán ser manejados 
// por el contenedor Spring
@Configuration
// Equivalente a tx de Spring en el xml
@EnableTransactionManagement
// Equivalente a context:component-scan base-package="..." en el xml, indica
// dónde buscar los beans controlados por Spring
@ComponentScan({ "org.sunnycake.aton.configuration" })
// Aquí se pueden especificar varias propiedades de Spring indicadas en el
// archivo con propiedades en el classpath
@PropertySource(value = { "classpath:aplicacion.properties" })
public class HibernateConfiguration {

	/**
	 * Intectado por Spring según el archivo .properties indicado en
	 * \@PropertySource
	 */
	@Autowired
	private Environment environment;
	
	/**
	 * Mensajes al archivo de logger.
	 */
	private Logger logger = LogManager.getLogger(HibernateConfiguration.class);

	/**
	 * Aquí se crea un session factory, equivalente a lo que iría en el archivo
	 * de configuración de Spring.
	 * 
	 * @return Session factory de Spring
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		// Se encadena con el método datasource
		sessionFactory.setDataSource(dataSource());
		// Se indican los paquetes que tendrán los POJO (DTO)
		sessionFactory.setPackagesToScan(new String[] { "org.sunnycake.aton.dto" });
		// Se asignan las propiedades de Hibernate
		sessionFactory.setHibernateProperties(hibernateProperties());
		logger.info("Session factory creado éxitosamente");
		return sessionFactory;
	}

	/**
	 * Propiedades de la base de datos (Según environment)
	 * 
	 * @return Nuevo DataSource (Configuración de la base de datos)
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		logger.info("Data source creado éxitosamente");
		return dataSource;
	}

	/**
	 * Propiedades de hibernate
	 * 
	 * @return Set de propiedades para la nueva configuración
	 */
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		// Dialecto (Mysql o postgresql, entre otros)
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		// Mostrar la sentencia SQL
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		logger.info("Propiedades de hibernate creadas éxitosamente");
		return properties;
	}

	/**
	 * Inyectado por sessionFactory
	 * 
	 * @param s
	 * @return
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		logger.info("Transaction Manager creado éxitosamente");
		return txManager;
	}
}
