package com.example.currencyexchange.exception;

import static com.example.currencyexchange.exception.ExceptionCreator.fromException;
import static com.example.currencyexchange.exception.ExceptionCreator.fromExchangeException;
import static com.example.currencyexchange.exception.ExceptionCreator.fromWebExchangeBindException;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import com.example.currencyexchange.config.ErrorProperties;
import com.example.currencyexchange.model.business.exception.ExchangeException;
import com.example.currencyexchange.model.expose.exception.ExchangeHttpException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Configuration
@AllArgsConstructor
@Order(-2)
@Slf4j
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

	private ObjectMapper objectMapper;

	private ErrorProperties props;

	@Override
	public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
		log.error("***Exception was", throwable);
		DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
		serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

		ExchangeHttpException customException = getHttpException(throwable);

		try {
			DataBuffer dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(customException));
			serverWebExchange.getResponse().setStatusCode(HttpStatusHandler.getStatus(throwable));
			return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
		} catch (JsonProcessingException e) {
			log.error("Error while sending exception", e);
		}

		return serverWebExchange.getResponse().writeWith(Mono.just(bufferFactory.wrap("Unknown error".getBytes())));
	}

	private ExchangeHttpException getHttpException(Throwable throwable) {
		if (throwable instanceof WebExchangeBindException) {
			return fromWebExchangeBindException((WebExchangeBindException) throwable, props);
		} else if (throwable instanceof ExchangeException) {
			return fromExchangeException((ExchangeException) throwable, props);
		} else {
			return fromException((Exception) throwable, props);
		}
	}

}