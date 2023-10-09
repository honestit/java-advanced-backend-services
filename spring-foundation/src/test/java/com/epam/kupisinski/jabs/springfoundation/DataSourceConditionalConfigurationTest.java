package com.epam.kupisinski.jabs.springfoundation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import javax.sql.DataSource;

import static com.google.common.truth.Truth.assertThat;

class DataSourceConditionalConfigurationTest {

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

  @Test
  public void shouldUseDefaultDataSourceWhenProvided() {
    contextRunner
        .withUserConfiguration(
            DataSourceConfiguration.class, DataSourceConditionalConfiguration.class)
        .run(
            context -> {
              assertThat(context.getBeansOfType(DataSource.class)).hasSize(1);
              assertThat(context.getBean(DataSource.class))
                  .isEqualTo(context.getBean(DataSourceConfiguration.class).dataSource());
            });
  }

  @Test
  public void shouldUseConditionalDataSourceWhenProvidedAndDefaultIsMissing() {
    contextRunner
        .withUserConfiguration(
            DataSourceConfiguration.class, DataSourceConditionalConfiguration.class)
        .run(
            context -> {
              assertThat(context.getBeansOfType(DataSource.class)).hasSize(1);
              assertThat(context.getBean(DataSource.class))
                  .isEqualTo(
                      context.getBean(DataSourceConditionalConfiguration.class).dataSource());
            });
  }
}
