package com.patriciocontreras.encuesta.models.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "gusto_musical")
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class GustoMusical implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_musica")
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private TipoMusica tipoMusica;
	
	@Column(unique=true)
	private String email;
	
	@Override
	public int hashCode() {
		return Objects.hash( usuario, tipoMusica,email );
	}
	
	
	

	public Usuario getUsuario() {
		return usuario;
	}




	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}




	public TipoMusica getTipoMusica() {
		return tipoMusica;
	}




	public void setTipoMusica(TipoMusica tipoMusica) {
		this.tipoMusica = tipoMusica;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
