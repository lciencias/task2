package com.example.springboot.backend.apirest.models.services;

import java.util.List;
import com.example.springboot.backend.apirest.models.entity.Authorization;

public interface IAuthorizationService {

	public List<Authorization> findAll();
	
	public Authorization findById(Long id);
	
	public Authorization save(Authorization categoria);
	
	public void delete(Long id);

}
