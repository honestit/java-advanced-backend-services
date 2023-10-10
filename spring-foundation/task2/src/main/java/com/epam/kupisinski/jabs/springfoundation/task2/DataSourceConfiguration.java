package com.epam.kupisinski.jabs.springfoundation.task2;

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
        .url("jdbc:h2:mem:spring-foundations-task2-custom;DB_CLOSE_ON_EXIT=FALSE")
        .username("task2")
        .password("pass")
        .build();
  }
}
