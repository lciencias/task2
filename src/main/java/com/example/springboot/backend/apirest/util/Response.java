package com.example.springboot.backend.apirest.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import com.example.springboot.backend.apirest.models.entity.Authorization;

public class Response {

	public static ResponseEntity<Map<String, Object>> getError(Map<String, Object> response, HttpStatus code) {
		return new ResponseEntity<Map<String, Object>>(response, code);
	}
	
	public static ResponseEntity<Authorization> getAuthorization(Authorization authorization, HttpStatus code){
		return new ResponseEntity<Authorization>(authorization, code);
	}
	
	public static List<String> getErrores(List<FieldError> list){
		return list.stream().map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage()).collect(Collectors.toList());			
	}

}
