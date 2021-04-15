package br.com.loanlibrary.api.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class ApiException {

	@JsonProperty("errors")
	private List<String> errors;
	
	public ApiException(BindingResult bindResult) {
		this.errors = new ArrayList<String>();
		bindResult.getAllErrors()
			.stream()
			.forEach(erro -> errors.add(erro.getDefaultMessage()));
	}
	
	public ApiException(BusinessException ex) {
		this.errors = new ArrayList<String>();
		this.errors = Arrays.asList(ex.getMessage());
	}
	
	public ApiException(String ex) {
		this.errors = new ArrayList<String>();
		this.errors = Arrays.asList(ex);
	}
}
