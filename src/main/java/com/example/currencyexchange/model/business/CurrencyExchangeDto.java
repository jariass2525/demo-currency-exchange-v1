package com.example.currencyexchange.model.business;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CurrencyExchangeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private RateDto rate;

	private BigDecimal amount;

	private BigDecimal changedAmount;

}
