package com.example.currencyexchange.model.expose;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RateForUpsert extends Rate {

	private static final long serialVersionUID = 1L;

	private static final String RATE = "^[0-9]+((\\,|\\.)[0-9]{1,2})?$";
	
	@NotBlank(message = "{exchangeRate.notblank}")
	@Pattern(regexp = RATE, message = "{exchangeRate.pattern}")
	@Override
	public String getExchangeRate() {
		return super.getExchangeRate();
	}
	
}
