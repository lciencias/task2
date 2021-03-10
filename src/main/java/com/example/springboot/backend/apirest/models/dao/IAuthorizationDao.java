package com.example.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.springboot.backend.apirest.models.entity.Authorization;

public interface IAuthorizationDao extends CrudRepository<Authorization, Long>{
	
	@Query("from Authorization")
	public List<Authorization> findAllAuthorization();	

}
