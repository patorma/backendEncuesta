package com.patriciocontreras.encuesta.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="tipo_musica")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class TipoMusica implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, max=90)
	@NotEmpty 
	@Column(nullable = false,unique=true) 
	private String nombre;
	
	/*@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private List<GustoMusical> gustosMusicales;*/
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
