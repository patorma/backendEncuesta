package com.patriciocontreras.encuesta.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.patriciocontreras.encuesta.models.dao.IUsuarioDao;

import com.patriciocontreras.encuesta.models.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		
		return usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		
		return usuarioDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		
		return usuarioDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Obtener el usuariom por username
				//b se toma la lista de roles(lado derecho) del tipo de la clase entity rol
				// un array List y lo convertimos a una coleccion o list de tipo GrantedAuthority
				// mediante el metodo map
				Usuario usuario = usuarioDao.findByUsername(username);
				if(usuario == null) {
					logger.error("Error en el login: no existe el usuario '"+username+"' en el sistema!");
					throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
				}
				
				List<GrantedAuthority> authorities = usuario.getRoles()
						.stream()
						.map(role -> new SimpleGrantedAuthority(role.getNombre()))
						.peek(authority -> logger.info("Role : " + authority.getAuthority()))
						.collect(Collectors.toList());
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}
	
	

	
}
