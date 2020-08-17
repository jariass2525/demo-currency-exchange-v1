package com.example.currencyexchange.model.business.exception;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ExchangeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;

	private String description;
	
	private String detail;
	
	private HttpStatus status;

}
