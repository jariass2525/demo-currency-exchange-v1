package com.example.currencyexchange.expose;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.currencyexchange.model.expose.CurrencyExchange;
import com.example.currencyexchange.model.expose.RateForUpsert;
import com.example.currencyexchange.service.CurrencyExchangeService;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class CurrencyExchangeController {

	private CurrencyExchangeMapper mapper;
	
	private CurrencyExchangeService service;
	
	@GetMapping("/currency-exchange/test")
	public Single<String> test() {
		return Single.just("It's alive!");
	}
	
	@PostMapping("/currency-exchange/calculate")
	public Single<CurrencyExchange> calculate(@RequestBody @Validated CurrencyExchange request) {
		return Single.fromCallable(() -> {
			log.info("HTTP request: {}, ", request);
			return mapper.exposeBeanToDto(request);
		})
		.subscribeOn(Schedulers.io())
		.flatMap(currencyExchange -> service.calculate(currencyExchange))
		.map(currencyExchange -> mapper.dtoToExposeBean(currencyExchange))
		.doOnSuccess(response -> log.info("HTTP response: {}, ", response));
	}
	
	@PostMapping("/currency-exchange")
	public Completable save(@RequestBody @Validated RateForUpsert request) {
		return Single.fromCallable(() -> {
			log.info("HTTP request: {}, ", request);
			return mapper.exposeBeanToRateDto(request);
		})
		.subscribeOn(Schedulers.io())
		.flatMapCompletable(rate -> service.save(rate))
		.doOnComplete(() -> log.info("Execution complete"));
	}
	
}
