package br.com.loanlibrary.api.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.loanlibrary.api.exception.ApiException;
import br.com.loanlibrary.api.exception.BusinessException;

@ControllerAdvice
public class ExceptionResponse {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult resultErrors = ex.getBindingResult();
		ApiException allErros = new ApiException(resultErrors);
		
		return ResponseEntity.badRequest().body(allErros);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handlerBusinessException(BusinessException ex) {
		
		ApiException allErros = new ApiException(ex);
		
		return ResponseEntity.badRequest().body(allErros);
	}
}
