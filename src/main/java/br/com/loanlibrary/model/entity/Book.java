package br.com.loanlibrary.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor @Entity @Table
public class Book {
	private Long codigo;	
	private String titulo;	
	private String autor;	
	private String isbn;	
	private LocalDateTime dataCadastro;
}
