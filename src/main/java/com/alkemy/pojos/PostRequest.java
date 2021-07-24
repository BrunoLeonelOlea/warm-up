package com.alkemy.pojos;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;


public class PostRequest {
	
	public Long id;
	public String titulo;
	public String contenido;
	public String imagen;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public LocalDateTime fechaDeCreacion;
	public long categoria_id;
	public long usuario_id;

}
