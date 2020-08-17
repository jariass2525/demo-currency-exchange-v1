package com.example.currencyexchange.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "application.error")
@Getter
@Setter
public class ErrorProperties {

	private Map<String, String> errorMap;

}
