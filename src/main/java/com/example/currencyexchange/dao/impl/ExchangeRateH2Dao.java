package com.example.currencyexchange.dao.impl;

import static com.example.currencyexchange.dao.impl.SqlConstants.COLUMN_ORIGIN_CURRENCY;
import static com.example.currencyexchange.dao.impl.SqlConstants.COLUMN_TARGET_CURRENCY;
import static com.example.currencyexchange.dao.impl.SqlConstants.*;
import static com.example.currencyexchange.model.business.exception.ExchageErrorCodes.RATE_NOT_FOUND;

import org.davidmoten.rx.jdbc.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.currencyexchange.dao.ExchangeRateDao;
import com.example.currencyexchange.model.business.RateDto;
import com.example.currencyexchange.model.business.exception.ExchangeException;
import com.example.currencyexchange.model.thirdparty.exchangerate.ExchangeRateEntity;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExchangeRateH2Dao implements ExchangeRateDao {

	@Autowired
	private Database database;

	@Override
	public Single<RateDto> find(RateDto rate) {
		log.debug("find init - {}", rate);
		
		return findByCurrencies(rate)
		.switchIfEmpty(Single.error(ExchangeException
				.builder()
				.code(RATE_NOT_FOUND.value)
				.status(HttpStatus.NOT_FOUND)
				.build()
				))
		.map(rateFromDb -> {
			rate.setExchangeRate(rateFromDb.exchangeRate());
			return rate;
		})
		.doOnSuccess(result -> log.info("debug end - {}", result));
	}

	private Maybe<ExchangeRateEntity> findByCurrencies(RateDto rate) {
		return database.select(SELECT)
				.parameter(COLUMN_ORIGIN_CURRENCY, rate.getOriginCurrency())
				.parameter(COLUMN_TARGET_CURRENCY, rate.getTargetCurrency())
				.autoMap(ExchangeRateEntity.class)
				.toObservable()
				.firstElement();
	}

	@Override
	public Completable save(RateDto rate) {
		log.debug("Before saving rate - {}", rate);
		Completable insert = database.update(INSERT)
			.parameter(COLUMN_ORIGIN_CURRENCY, rate.getOriginCurrency())
			.parameter(COLUMN_TARGET_CURRENCY, rate.getTargetCurrency())
			.parameter(COLUMN_EXCHANGE_RATE, rate.getExchangeRate())
			.complete();
		
		Completable update = database.update(UPDATE)
			.parameter(COLUMN_EXCHANGE_RATE, rate.getExchangeRate())
			.parameter(COLUMN_ORIGIN_CURRENCY, rate.getOriginCurrency())
			.parameter(COLUMN_TARGET_CURRENCY, rate.getTargetCurrency())
			.complete();
		
		return findByCurrencies(rate)
				.isEmpty()
				.flatMapCompletable(isEmpty -> isEmpty ? insert : update);
	}

}
