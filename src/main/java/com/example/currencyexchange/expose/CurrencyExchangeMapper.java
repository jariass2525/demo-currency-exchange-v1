package com.example.currencyexchange.expose;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.example.currencyexchange.model.business.CurrencyExchangeDto;
import com.example.currencyexchange.model.business.RateDto;
import com.example.currencyexchange.model.expose.CurrencyExchange;
import com.example.currencyexchange.model.expose.Rate;
import com.example.currencyexchange.model.expose.RateForUpsert;

@Mapper(componentModel = "spring")
public interface CurrencyExchangeMapper {

	public static final String DECIMAL_PATTERN = "0.00";
	
	@Mappings({ 
		@Mapping(target = "rate.exchangeRate", ignore = true),
		@Mapping(target = "changedAmount", ignore = true),
		@Mapping(target = "amount", numberFormat = DECIMAL_PATTERN)
	})
	CurrencyExchangeDto exposeBeanToDto(CurrencyExchange bean);
	
	@Mappings({ 
		@Mapping(target = "amount", numberFormat = DECIMAL_PATTERN),
		@Mapping(target = "changedAmount", numberFormat = DECIMAL_PATTERN)
	})
	CurrencyExchange  dtoToExposeBean(CurrencyExchangeDto dto);
	
	@Mappings({ 
		@Mapping(target = "exchangeRate", numberFormat = DECIMAL_PATTERN)
	})
	Rate dtoToRateBean(RateDto dto);

	@Mappings({ 
		@Mapping(target = "exchangeRate", numberFormat = DECIMAL_PATTERN),
	})
	RateDto exposeBeanToRateDto(RateForUpsert bean);
	
}
