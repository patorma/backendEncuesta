package com.patriciocontreras.encuesta.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patriciocontreras.encuesta.models.dao.IGustoMusicalDao;
import com.patriciocontreras.encuesta.models.entity.GustoMusical;

@Service
public class GustoMusicalServiceImpl implements IGustoMusicalService {
	
	@Autowired
	private IGustoMusicalDao gustoMusicalDao;

	@Override
	@Transactional(readOnly = true)
	public List<GustoMusical> findAll() {
		
		return gustoMusicalDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<GustoMusical> findAll(Pageable pageable) {
		
		return gustoMusicalDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public GustoMusical findById(Long id) {
		
		return gustoMusicalDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public GustoMusical save(GustoMusical gastoMusical) {
		
		return gustoMusicalDao.save(gastoMusical) ;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		gustoMusicalDao.deleteById(id);

	}

}
