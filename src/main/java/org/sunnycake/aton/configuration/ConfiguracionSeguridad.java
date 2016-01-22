package org.sunnycake.aton.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuraciones de seguridad Spring.
 *
 * @author Camilo Sampedro
 *
 */
@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

    /**
     * Servicio para obtener la información de un usuario
     */
    @Autowired
    @Qualifier("userDetailsServiceAton")
    UserDetailsService userDetailsService;

    /**
     * Configuraciones de autenticación (Se puede agregar un encriptador).
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Permisos en los diferentes path.
     *
     * @param http
     * @throws java.lang.Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Se permite atodos el acceso a / y /equipos. A los ADMIN a /admin* y a
        // los DBA y ADMIN a /db*
        http.authorizeRequests().antMatchers("/", "/equipos").permitAll().antMatchers("/admin/**")
                .access("hasRole('ADMIN')").antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')").and()
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").and()
                .csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }

    /**
     * Codificar los password a la hora de insertarlos
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}
