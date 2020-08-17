package com.example.currencyexchange.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.example.currencyexchange.model.business.exception.ExchangeException;

public class HttpStatusHandler {

	private static Map<Class<? extends Throwable>, HttpStatus> statusMap = new HashMap<>();

	static {
		statusMap.put(WebExchangeBindException.class, BAD_REQUEST);
	}

	public static HttpStatus getStatus(Throwable e) {
		if (e instanceof ExchangeException) {
			return ((ExchangeException)e).getStatus();
		}
		HttpStatus value = statusMap.get(e.getClass());
		return value != null ? value : HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
