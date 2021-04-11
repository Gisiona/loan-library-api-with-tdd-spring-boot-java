package br.com.loanlibrary.api.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.loanlibrary.api.exception.ApiException;

@ControllerAdvice
public class ExceptionResponse {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerBusinessException(MethodArgumentNotValidException ex) {
		BindingResult resultErrors = ex.getBindingResult();
		ApiException allErros = new ApiException(resultErrors);
		
		return ResponseEntity.badRequest().body(allErros);
	}
}
