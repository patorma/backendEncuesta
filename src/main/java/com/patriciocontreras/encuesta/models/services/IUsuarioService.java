package com.patriciocontreras.encuesta.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.patriciocontreras.encuesta.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();	
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);
	
	public Usuario findByUsername(String username); 
	
	//public GustoMusical findTipoMusicaByIdAndEmail(Long idTipoMusica,String email);
	
}
