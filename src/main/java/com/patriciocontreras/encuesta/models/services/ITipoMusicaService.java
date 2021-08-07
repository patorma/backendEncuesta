package com.patriciocontreras.encuesta.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.patriciocontreras.encuesta.models.entity.TipoMusica;

public interface ITipoMusicaService {
	
	 public List<TipoMusica> findAll();
	 
	 public Page<TipoMusica> findAll(Pageable pageable);
	 
	 public TipoMusica findById(Long id);
	 
	 public TipoMusica save(TipoMusica tipoMusica);
	 
	 public void delete(Long id);
	 
	 

}
