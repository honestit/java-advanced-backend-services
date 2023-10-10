package com.epam.kupisinski.jabs.springfoundation;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataSourceProfileBasedConfiguration {

  @Bean(name = "dataSource")
  @Profile("QA")
  DataSource qaDataSource() {
    return DataSourceBuilder.create()
        .driverClassName("org.h2.Driver")
        .url("jdbc:h2:mem:spring-foundation-qa;CLOSE_DB_ON_EXIT=FALSE")
        .username("qa-user")
        .password("qa")
        .build();
  }

  @Bean("dataSource")
  @Profile("DEV")
  DataSource devDataSource() {
    return DataSourceBuilder.create()
        .driverClassName("org.h2.Driver")
        .url("jdbc:h2:mem:spring-foundation-dev;CLOSE_DB_ON_EXIT=FALSE")
        .username("dev-user")
        .password("dev")
        .build();
  }
}
