package com.epam.kupisinski.jabs.springfoundation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import javax.sql.DataSource;

import static com.google.common.truth.Truth.assertThat;

class DataSourceConfigurationTest {

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

  @Test
  public void shouldHaveH2DataSourceBean() {
    contextRunner
        .withUserConfiguration(DataSourceConfiguration.class)
        .run(
            context -> {
              assertThat(context.getBeansOfType(DataSource.class)).hasSize(1);
              assertThat(context.getBean(DataSource.class))
                  .isEqualTo(context.getBean(DataSourceConfiguration.class).dataSource());
            });
  }
}
