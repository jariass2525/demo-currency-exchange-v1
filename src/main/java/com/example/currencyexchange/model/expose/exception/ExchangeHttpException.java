package com.example.currencyexchange.model.expose.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
public class ExchangeHttpException implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;

	private String description;
	
	private String detail;

}
