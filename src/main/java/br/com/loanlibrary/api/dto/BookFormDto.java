package br.com.loanlibrary.api.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class BookFormDto {
	
	@JsonProperty("codigo")
	private Long codigo;
	
	@NotEmpty(message = "O título não pode ser nulo.")
	@JsonProperty("titulo")
	private String titulo;
	
	@NotEmpty(message = "O autor não pode ser nulo.")
	@JsonProperty("autor")
	private String autor;
	
	@NotEmpty(message = "O isbn não pode ser nulo.")
	@JsonProperty("isbn")
	private String isbn;
}
