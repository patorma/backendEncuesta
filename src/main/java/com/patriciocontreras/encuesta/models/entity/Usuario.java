package com.patriciocontreras.encuesta.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length = 20)
	private String username;
	
	@Column(length = 60)
	private String password;
	
	
	private Boolean enabled;
	
	private String nombre;
	private String apellido;
	
	@ManyToMany(fetch =  FetchType.LAZY)
	@JoinTable(name = "usuarios_roles",joinColumns = @JoinColumn(name="usuario_id")
	,inverseJoinColumns = @JoinColumn(name="role_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})})
	private List<Role> roles;
	
	/*@ManyToMany(fetch =  FetchType.LAZY)
	@JoinTable(name = "gustos_musicales",joinColumns = @JoinColumn(name="usuario_id"),
	inverseJoinColumns = @JoinColumn(name="tipo_musica_id"), 
	uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","tipo_musica_id"})})
	private List<TipoMusica> tipos;*/
	
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "usuario")
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private List<GustoMusical> gustosMusicales;*/
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
