package com.example.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.backend.apirest.models.dao.IAuthorizationDao;
import com.example.springboot.backend.apirest.models.entity.Authorization;

@Service
public class AuthorizationServiceImpl implements IAuthorizationService {

	@Autowired
	private IAuthorizationDao authorizationDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Authorization> findAll() {
		return (List<Authorization>) authorizationDao.findAll();
		
	}

	@Override
	@Transactional(readOnly = true)
	public Authorization findById(Long id) {
		return authorizationDao.findById(id).orElse(null);	
	}

	@Override
	@Transactional
	public Authorization save(Authorization authorization) {
		return authorizationDao.save(authorization);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		authorizationDao.deleteById(id);		
	}
}
