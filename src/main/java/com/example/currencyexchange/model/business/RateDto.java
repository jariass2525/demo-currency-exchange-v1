package com.example.currencyexchange.model.business;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RateDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String originCurrency;

	private String targetCurrency;

	private BigDecimal exchangeRate;

}
