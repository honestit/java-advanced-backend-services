package com.epam.kupisinski.jabs.springfoundation;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

  @Bean
  DataSource dataSource() {
    return DataSourceBuilder.create()
        .driverClassName("org.h2.Driver")
        .url("jdbc:h2:mem:spring-foundation-1;DB_CLOSE_ON_EXIT=FALSE")
        .username("admin")
        .password("s3cr3t")
        .build();
  }
}
