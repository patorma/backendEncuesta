package com.patriciocontreras.encuesta.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.patriciocontreras.encuesta.models.entity.TipoMusica;
import com.patriciocontreras.encuesta.models.services.ITipoMusicaService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api")
public class TipoMusicaController {
	
	@Autowired
	private ITipoMusicaService tipoMusicaService;
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/tipos")
	public List<TipoMusica> index(){
		return tipoMusicaService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/tipos/page/{page}")
	public Page<TipoMusica> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 4);
		return tipoMusicaService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/tipos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		
		TipoMusica tipoMusica = null;
		Map<String, Object> response  = new HashMap<>();
		
		try {
			tipoMusica = tipoMusicaService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(tipoMusica == null) {
			response.put("mensaje", "El tipo de musica con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<TipoMusica>(tipoMusica,HttpStatus.OK); 
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/tipos")
	public ResponseEntity<?> create(@Valid @RequestBody TipoMusica tipoMusica ,BindingResult result){
		
		TipoMusica tipoMusicaNew = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			// se debe obtener los mensajes de errror de cada campo 
			// y convertir estos en una lista de errores de tipo string
			// se debe convertir esta lista de fielderrors en String
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+ err.getField() + "' "+err.getDefaultMessage())//muy parecido  al operador map en angular (rxjs), mismo concepto!
					.collect(Collectors.toList());// ahora podemos convertir de regreso el stream  aun tipo List
			response.put("errors", errors);
			// se responde con un responseentity con listados de error
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
			// en lo anterior se recibe un field errors y lo convertimos a string
		}
		
		try {
			tipoMusicaNew = tipoMusicaService.save(tipoMusica);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		//se podria pasar un map con un mensaje y con el tipo de musica creado
		response.put("mensaje", "El tipo de musica ha sido creado con éxito! ");
		response.put("tipo de musica", tipoMusicaNew);
		

		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
	}
	@Secured("ROLE_ADMIN")
	@PutMapping("/tipos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody TipoMusica tipoMusica,BindingResult result,@PathVariable Long id){
		//obtenemos el tipo musica que queremos modificar de la bd por Id
		TipoMusica tipoMusicaActual = tipoMusicaService.findById(id);
		
		//Tipo musica ya actualizado
		TipoMusica  tipoMusicaUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			// se debe obtener los mensajes de errror de cada campo 
			// y convertir estos en una lista de errores de tipo string
			
			// se debe convertir esta lista de fielderrors en String
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+ err.getField() + "' "+err.getDefaultMessage())// muy parecido  al operador map en angular (rxjs), mismo concepto!
					.collect(Collectors.toList());// ahora podemos convertir de regreso el stream  aun tipo List
			response.put("errors", errors);
			// se responde con un responseentity con listados de error
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
			
			// en lo anterior se recibe un field errors y lo convertimos a string
		}
		
		if(tipoMusicaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el tipo de musica con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			tipoMusicaActual.setNombre(tipoMusica.getNombre());
			
			tipoMusicaUpdated = tipoMusicaService.save(tipoMusicaActual);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el tipo de musica en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El tipo de musica ha sido actualizado con éxito!");
		response.put("tipo de musica", tipoMusicaUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED) ;
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/tipos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		try {
			tipoMusicaService.delete(id);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el tipo de musica de la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El tipo de musica fue eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}

}
