package com.example.currencyexchange.model.expose;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
public class CurrencyExchange implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String AMOUNT = "^[0-9]+((\\,|\\.)[0-9]{1,2})?$";
	
	@NotNull(message = "{currencyExchange.rate.notnull}")
	@Valid
	private Rate rate;

	@NotBlank(message = "{currencyExchange.amount.notblank}")
	@Pattern(regexp = AMOUNT, message = "{currencyExchange.amount.pattern}")
	private String amount;

	private String changedAmount;

}
