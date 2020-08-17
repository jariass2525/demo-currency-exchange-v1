package com.example.currencyexchange.model.expose;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
public class Rate implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String CURRENCY = "^[A-Z]{3}$";
	
	@NotBlank(message = "{currencyExchange.rate.originCurrency.notblank}")
	@Pattern(regexp = CURRENCY, message = "{currencyExchange.rate.originCurrency.pattern}")
	private String originCurrency;

	@NotBlank(message = "{currencyExchange.rate.targetCurrency.notblank}")
	@Pattern(regexp = CURRENCY, message = "{currencyExchange.rate.targetCurrency.pattern}")
	private String targetCurrency;

	private String exchangeRate;

}
