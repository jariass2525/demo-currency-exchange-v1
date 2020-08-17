package com.example.currencyexchange.model.business.exception;

public enum ExchageErrorCodes {

	INPUT_VALIDATION("ERROR-001"), 
	RATE_NOT_FOUND("ERROR-002"),
	UNEXPECTED("ERROR-999");;

	public final String value;

	private ExchageErrorCodes(String value) {
		this.value = value;
	}
}
