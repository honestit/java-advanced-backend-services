package com.epam.kupisinski.jabs.springfoundation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;

import static com.google.common.truth.Truth.assertThat;

class DataSourceConditionalConfigurationTest {

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

  @Test
  public void shouldUseDefaultDataSourceWhenProvided() {
    contextRunner
        .withConfiguration(AutoConfigurations.of(DataSourceConditionalConfiguration.class))
        .withUserConfiguration(DataSourceConfiguration.class)
        .run(
            context -> {
              DatabaseMetaData metaData =
                  context.getBean(DataSource.class).getConnection().getMetaData();
              assertThat(metaData.getURL()).startsWith("jdbc:h2:mem:spring-foundation-1");
            });
  }

  @Test
  public void shouldUseConditionalDataSourceWhenProvidedAndDefaultIsMissing() {
    contextRunner
        .withConfiguration(AutoConfigurations.of(DataSourceConditionalConfiguration.class))
        .run(
            context -> {
              DatabaseMetaData metaData =
                  context.getBean(DataSource.class).getConnection().getMetaData();
              assertThat(metaData.getURL()).startsWith("jdbc:h2:mem:spring-foundation-2");
            });
  }
}
