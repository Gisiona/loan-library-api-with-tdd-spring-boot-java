package br.com.loanlibrary.api.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
	
	public BusinessException(String msg) {
		super(msg);
	}	
}
