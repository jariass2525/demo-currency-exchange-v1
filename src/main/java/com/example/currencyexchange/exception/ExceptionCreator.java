package com.example.currencyexchange.exception;

import static com.example.currencyexchange.model.business.exception.ExchageErrorCodes.INPUT_VALIDATION;
import static com.example.currencyexchange.model.business.exception.ExchageErrorCodes.UNEXPECTED;

import org.springframework.web.bind.support.WebExchangeBindException;

import com.example.currencyexchange.config.ErrorProperties;
import com.example.currencyexchange.model.business.exception.ExchangeException;
import com.example.currencyexchange.model.expose.exception.ExchangeHttpException;

public class ExceptionCreator {

	public static ExchangeHttpException fromWebExchangeBindException(WebExchangeBindException exception,
			ErrorProperties props) {
		StringBuilder detailBuilder = new StringBuilder();
		exception.getBindingResult().getFieldErrors().stream()
				.forEach(fieldError ->
					detailBuilder.append(fieldError.getField())
					.append(": ")
					.append(fieldError.getDefaultMessage())
					.append(","));
		String detail = detailBuilder.toString();
		return ExchangeHttpException.builder()
				.code(INPUT_VALIDATION.value)
				.description(props.getErrorMap().get(INPUT_VALIDATION.value))
				.detail(detail.substring(0, detail.length()-1))
				.build();
	}

	public static ExchangeHttpException fromException(Exception exception, ErrorProperties props) {
		return ExchangeHttpException.builder()
				.code(UNEXPECTED.value)
				.description(props.getErrorMap().get(UNEXPECTED.value))
				.detail(exception.getMessage())
				.build();
	}

	public static ExchangeHttpException fromExchangeException(ExchangeException exception, ErrorProperties props) {
		String errorCode = exception.getCode();
		return ExchangeHttpException.builder()
				.code(errorCode)
				.description(props.getErrorMap().get(errorCode))
				.detail(exception.getDetail())
				.build();
	}

}
