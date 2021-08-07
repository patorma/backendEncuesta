package com.patriciocontreras.encuesta.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patriciocontreras.encuesta.models.entity.GustoMusical;

public interface IGustoMusicalDao extends JpaRepository<GustoMusical, Long > {

}
