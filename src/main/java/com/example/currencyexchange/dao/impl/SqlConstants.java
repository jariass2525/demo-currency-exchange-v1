package com.example.currencyexchange.dao.impl;

public class SqlConstants {
	
	private SqlConstants() {

	}
	
	static final String SELECT = "SELECT id,origin_currency,target_currency,exchange_rate "
			+ "from public.EXCHANGE_RATE where origin_currency=:origin_currency "
			+ "AND target_currency=:target_currency";
	
	static final String INSERT = "INSERT INTO public.EXCHANGE_RATE "
			+ "(origin_currency, target_currency, exchange_rate) "
			+ "VALUES (:origin_currency, :target_currency, :exchange_rate)";
	
	static final String UPDATE = "UPDATE public.EXCHANGE_RATE "
			+ "set exchange_rate=:exchange_rate "
			+ "where origin_currency=:origin_currency "
			+ "AND target_currency=:target_currency";
	
	static final String COLUMN_ORIGIN_CURRENCY = "origin_currency";
	
	static final String COLUMN_TARGET_CURRENCY = "target_currency";
	
	static final String COLUMN_EXCHANGE_RATE = "exchange_rate";

}
