package com.epam.kupisinski.jabs.springfoundation.task4;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class ProfileSpecificDataSourceConfiguration {

  @Profile("QA")
  @Bean(name = "dataSource")
  DataSource qaDataSource() {
    return DataSourceBuilder.create()
        .driverClassName("org.h2.Driver")
        .url("jdbc:h2:mem:spring-foundations-qa;DB_CLOSE_ON_EXIT=FALSE")
        .username("qa")
        .password("pass")
        .build();
  }

  @Profile("DEV")
  @Bean(name = "dataSource")
  DataSource devDataSource() {
    return DataSourceBuilder.create()
        .driverClassName("org.h2.Driver")
        .url("jdbc:h2:mem:spring-foundations-dev;DB_CLOSE_ON_EXIT=FALSE")
        .username("qa")
        .password("pass")
        .build();
  }
}
