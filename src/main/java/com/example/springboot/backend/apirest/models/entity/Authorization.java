package com.example.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "authorizations")
public class Authorization  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "es obligatorio")
	@Size(min = 4, max = 120, message = "el tamaño tiene que estar entre 4 y 120")
	@Column(nullable = false)
	private String description;

	
	/**
	 * En caso de que el nombre del campo conincida con la variable 
	 * no es necesario poner la etiqueta @column name
	 */
	
	@NotNull(message="es obligatorio")
	@Column(name = "amount")  
	private Double amount;
	
	@NotEmpty(message = "es obligatorio")
	@Column(name = "estatus", columnDefinition = "varchar(50) default 'En Proceso'")
	private String estatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	
	
}
