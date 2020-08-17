package com.example.currencyexchange.service.impl;

import org.springframework.stereotype.Service;

import com.example.currencyexchange.dao.ExchangeRateDao;
import com.example.currencyexchange.model.business.CurrencyExchangeDto;
import com.example.currencyexchange.model.business.RateDto;
import com.example.currencyexchange.service.CurrencyExchangeService;

import io.reactivex.Completable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

	private ExchangeRateDao dao;

	@Override
	public Single<CurrencyExchangeDto> calculate(CurrencyExchangeDto currencyExchange) {
		return dao.find(currencyExchange.getRate())
		.map(rate -> {
			log.info("Before setting rate - {}", rate);
			currencyExchange.setChangedAmount(currencyExchange.getAmount()
					.multiply(rate.getExchangeRate()));
			currencyExchange.setRate(rate);
			return currencyExchange;
		});
	}

	@Override
	public Completable save(RateDto rate) {
		return dao.save(rate);
	}

}
