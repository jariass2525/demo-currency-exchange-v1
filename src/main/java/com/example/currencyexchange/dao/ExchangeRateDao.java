package com.example.currencyexchange.dao;

import com.example.currencyexchange.model.business.RateDto;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface ExchangeRateDao {

	Single<RateDto> find(RateDto rate);

	Completable save(RateDto rate);

}
