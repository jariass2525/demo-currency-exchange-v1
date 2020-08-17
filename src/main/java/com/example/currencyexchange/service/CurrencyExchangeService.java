package com.example.currencyexchange.service;

import com.example.currencyexchange.model.business.CurrencyExchangeDto;
import com.example.currencyexchange.model.business.RateDto;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface CurrencyExchangeService {

	Single<CurrencyExchangeDto> calculate(CurrencyExchangeDto currencyExchange);

	Completable save(RateDto rate);

}
