package com.epam.kupisinski.jabs.springfoundation.task1;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@AutoConfiguration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DefaultDataSourceAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  DataSource dataSource() {
    return DataSourceBuilder.create()
        .driverClassName("org.h2.Driver")
        .url("jdbc:h2:mem:spring-foundations-task1-default;DB_CLOSE_ON_EXIT=FALSE")
        .username("task1")
        .password("pass")
        .build();
  }
}
