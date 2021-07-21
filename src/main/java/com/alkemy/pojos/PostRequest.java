package com.alkemy.pojos;

import java.time.LocalDate;


public class PostRequest {
	
	public Long id;
	public String titulo;
	public String contenido;
	public String imagen;
	public LocalDate fechaDeCreacion;
	public long categoria_id;
	public long usuario_id;

}
