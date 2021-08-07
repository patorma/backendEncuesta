package com.patriciocontreras.encuesta.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.patriciocontreras.encuesta.models.entity.GustoMusical;

public interface IGustoMusicalService {

	public List<GustoMusical> findAll();
	
	public Page<GustoMusical> findAll(Pageable pageable);
	
	public GustoMusical findById(Long id);
	
	
	public GustoMusical save(GustoMusical gastoMusical);
	
	 public void delete(Long id);
}
