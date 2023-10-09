package com.epam.kupisinski.jabs.springfoundation;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@AutoConfiguration
public class DataSourceConditionalConfiguration {

  @Bean
  @ConditionalOnMissingBean
  DataSource dataSource() {
    return DataSourceBuilder.create()
        .driverClassName("org.h2.Driver")
        .url("jdbc:h2:mem:spring-foundation-2;DB_CLOSE_ON_EXIT=FALSE")
        .username("user")
        .password("pass")
        .build();
  }
}
