package com.epam.kupisinski.jabs.springfoundation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
class SpringFoundationApplicationTests {

  @Autowired private DataSource dataSource;

  @Test
  public void shouldUseProvidedDataSource() throws Exception {
    DatabaseMetaData metaData = dataSource.getConnection().getMetaData();

    assertThat(metaData.getURL()).startsWith("jdbc:h2:mem:spring-foundation-1");
  }
}
