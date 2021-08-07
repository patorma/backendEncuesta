package com.patriciocontreras.encuesta.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.patriciocontreras.encuesta.models.entity.GustoMusical;
import com.patriciocontreras.encuesta.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
	
public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario findByUsername2(String username);
	
	

	
	//public GustoMusical findTipoMusicaByIdAndEmail(Long idTipoMusica,String email);

}
