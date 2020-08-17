package com.example.currencyexchange.config;

import javax.sql.DataSource;

import org.davidmoten.rx.jdbc.Database;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RxJava2JdbcConfig {

	@Bean
	public DataSource dataSource() {
		try {
			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			return builder.setType(EmbeddedDatabaseType.H2)
					.addScripts("schema.sql", "data.sql")
					.build();
		} catch (Exception ex) {
			log.error("Error creating datasource", ex);
			return null;
		}
	}

	@Bean
	public Database databaseBean(DataSource dataSource) {
		return Database.nonBlocking()
				.connectionProvider(dataSource)
				.maxPoolSize(5).build();
	}

}
