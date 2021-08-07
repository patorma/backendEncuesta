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

import com.patriciocontreras.encuesta.models.entity.GustoMusical;
import com.patriciocontreras.encuesta.models.services.IGustoMusicalService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api")
public class GustoMusicalController {
	
	@Autowired
	private IGustoMusicalService gustoMusicalService;
	
	@GetMapping("/gustos")
	public List<GustoMusical> index(){
		return gustoMusicalService.findAll();
	}
	
	@GetMapping("/gustos/page/{page}")
	public Page<GustoMusical> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 4);
		return gustoMusicalService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/gustos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		
		GustoMusical gustoMusical = null;
		Map<String, Object> response  = new HashMap<>();
		try {
			gustoMusical= gustoMusicalService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(gustoMusical == null) {
			response.put("mensaje", "El gastoMusical con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<GustoMusical>(gustoMusical,HttpStatus.OK); 
		
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/gustos")
	public ResponseEntity<?> create(@Valid @RequestBody GustoMusical gustoMusical,BindingResult result){
		
		GustoMusical gustoMusicalNew = null;
		Map<String, Object> response = new HashMap<>();
		
		// se valida si contiene errores el objeto 
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
				gustoMusicalNew = gustoMusicalService.save(gustoMusical);
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al realizar el insert en la base de datos!");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			//se podria pasar un map con un mensaje y con el gusto musical creado
			response.put("mensaje", "El gusto musical ha sido creado con éxito! ");
			response.put("gusto musical",gustoMusicalNew);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/gustos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody GustoMusical gustoMusical,BindingResult result,@PathVariable Long id){
		
		GustoMusical gustoMusicalActual = gustoMusicalService.findById(id);
		GustoMusical gustoMusicalUpdated = null;
		
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
		
		if(gustoMusicalActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el gusto musical con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			gustoMusicalActual.setEmail(gustoMusical.getEmail());
			
			gustoMusicalUpdated = gustoMusicalService.save(gustoMusicalActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el gusto musical en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El gusto musical ha sido actualizado con éxito!");
		response.put("gusto musical",gustoMusicalUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/gustos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		//Map para guardar el contenido que enviaremos en el ResponseEntity con mensajes
		Map<String, Object> response = new HashMap<>();
		
		try {
			gustoMusicalService.delete(id);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el gasto de la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El gusto musical fue eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}

}
