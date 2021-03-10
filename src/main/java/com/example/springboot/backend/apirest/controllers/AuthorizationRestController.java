package com.example.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.backend.apirest.models.entity.Authorization;
import com.example.springboot.backend.apirest.models.services.IAuthorizationService;
import com.example.springboot.backend.apirest.util.Response;


@RestController
@RequestMapping("/api")
public class AuthorizationRestController {
	
	@Autowired
	private IAuthorizationService authorizationService;
	  

	/*
	 * Metodo Get que regresa todas las autorizaciones
	 */
	@GetMapping("/authorizations")
	public List<Authorization> index() {
		return authorizationService.findAll();
	}
		

	/*
	 * Metodo Get que regresa una autorización buscada por un ID
     */
	@GetMapping("/authorizations/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Authorization authorization = null;
		Map<String, Object> response = new HashMap<>();
		try {
			authorization = authorizationService.findById(id);
		} 
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return Response.getError(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(authorization == null) {
			response.put("mensaje", "La autorización con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return Response.getError(response, HttpStatus.valueOf(422));
		}
		return Response.getAuthorization(authorization, HttpStatus.OK);
	}
	
	/*
	 * Metodo Post que guarda una autorizacion
	 * Nota: Este metodo se puede utilizar tambien como update, pero se debe de pasar el id con su valor
	 * En el metodo save, si el id viene vacio hace un insert en caso contrario un update
	 */
	@PostMapping("/authorizations")
	public ResponseEntity<?> create(@Valid @RequestBody Authorization authorization, BindingResult result) {		
		Authorization authorizationNew = null;
		Map<String, Object> response = new HashMap<>();		
		if(result.hasErrors()) {
			response.put("errors", Response.getErrores(result.getFieldErrors()));
			return Response.getError(response, HttpStatus.BAD_REQUEST);
		}
	
		try {
			authorizationNew = authorizationService.save(authorization);
			response.put("mensaje", "La autorización ha sido creado con éxito!");
			response.put("authorization", authorizationNew);
			return Response.getError(response, HttpStatus.OK);			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar registrar la autorización en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return Response.getError(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	

	/*
	 * Metodo Put para actualizar una autorizacion
	 */
	@PutMapping("/authorizations/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Authorization authorization, BindingResult result, @PathVariable Long id) {

		Authorization authorizationActual  = authorizationService.findById(id);
		Authorization authorizationUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			response.put("errors", Response.getErrores(result.getFieldErrors()));
			return Response.getError(response, HttpStatus.BAD_REQUEST);
		}
		
		if (authorizationActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la autorización ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));	
			return Response.getError(response, HttpStatus.BAD_REQUEST);
		}

		try {
			authorizationActual.setDescription(authorization.getDescription());
			authorizationActual.setAmount(authorization.getAmount());
			authorizationActual.setEstatus(authorization.getEstatus());
			authorizationUpdated = authorizationService.save(authorizationActual);
			response.put("mensaje", "La autorización ha sido actualizada con éxito!");
			response.put("authorization", authorizationUpdated);
			return Response.getError(response, HttpStatus.OK);
		}
		catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la autorización en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return Response.getError(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	/*
	 * Metodo Delete para eliminar una autorizacion
	 */
	@DeleteMapping("/authorizations/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			authorizationService.delete(id);
			response.put("mensaje", "La autorización se ha eliminado con éxito!");	
			return Response.getError(response, HttpStatus.OK);
		} 
		catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la autorización de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return Response.getError(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}	
}
