package com.example.currencyexchange.model.thirdparty.exchangerate;

import java.math.BigDecimal;

import org.davidmoten.rx.jdbc.annotations.Column;

public interface ExchangeRateEntity {

	@Column
	public Long id();

	@Column
	public String originCurrency();

	@Column
	public String targetCurrency();

	@Column
	public BigDecimal exchangeRate();

}
