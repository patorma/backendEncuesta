package com.patriciocontreras.encuesta.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patriciocontreras.encuesta.models.dao.ITipoMusicaDao;
import com.patriciocontreras.encuesta.models.entity.TipoMusica;

@Service
public class TipoMusicaServiceImpl implements ITipoMusicaService {
	
	@Autowired
	private ITipoMusicaDao tipoMusicaDao;

	@Override
	@Transactional(readOnly = true)
	public List<TipoMusica> findAll() {
		
		return tipoMusicaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TipoMusica> findAll(Pageable pageable) {
		
		return tipoMusicaDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public TipoMusica findById(Long id) {
		
		return tipoMusicaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public TipoMusica save(TipoMusica tipoMusica) {
		
		return tipoMusicaDao.save(tipoMusica);
	}

	@Override
	@Transactional
	public void delete(Long id) {
	    tipoMusicaDao.deleteById(id);

	}

}
