package org.sunnycake.aton.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.sunnycake.aton.dto.Rol;
import org.sunnycake.aton.dto.UsuarioWeb;
import org.sunnycake.aton.service.UsuarioWebService;

@Service("userDetailsServiceAton")
public class UserDetailsServiceAton implements UserDetailsService {

	private Logger logger = LogManager.getLogger(UserDetailsServiceAton.class);

	@Autowired
	private UsuarioWebService usuarioWebService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		UsuarioWeb usuarioWeb = usuarioWebService.obtenerUsuarioWebPorUsuario(usuario);
		logger.debug("Usuario (" + usuario + "): " + usuarioWeb);
		if (usuarioWeb == null) {
			throw new UsernameNotFoundException(usuario);
		}
		return new User(usuarioWeb.getUsuario(), usuarioWeb.getPassword(), usuarioWeb.isEnabled(), true, true, true,
				obtenerAutoridades(usuarioWeb));
	}

	private List<GrantedAuthority> obtenerAutoridades(UsuarioWeb usuarioWeb) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Rol rol : usuarioWeb.getRolesDeUsuario()) {
			logger.debug("Rol: " + rol);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getRol()));
		}
		logger.debug("Autoridades: " + authorities);
		return authorities;
	}

}
