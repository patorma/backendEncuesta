package com.patriciocontreras.encuesta.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patriciocontreras.encuesta.models.entity.TipoMusica;

public interface ITipoMusicaDao extends JpaRepository<TipoMusica, Long>{

}
