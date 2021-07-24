package com.alkemy.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "status = true")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String titulo;
	@NotNull
	private String contenido;
	@NotNull
	private String imagen;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = Shape.STRING)
	private LocalDateTime fechaDeCreacion;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	@NotNull
	private Category categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	@NotNull
	private User user;
	
	@Column(name = "status",columnDefinition = "boolean DEFAULT true")
	private Boolean status = true;
}
