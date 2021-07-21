package com.alkemy.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String contenido;
	
	private String imagen;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private LocalDate fechaDeCreacion;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Category categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")	
	private User user;
}
