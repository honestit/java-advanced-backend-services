package com.epam.kupisinski.jabs.springfoundation.task1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
class Task1ApplicationTests {

  @Autowired private DataSource dataSource;

  @Autowired private ThingRepository thingRepository;

  @Test
  public void shouldUseDataSourceFromAutoConfiguration() throws Exception {
    DatabaseMetaData metaData = dataSource.getConnection().getMetaData();

    assertThat(metaData.getURL()).startsWith("jdbc:h2:mem:spring-foundations-task1");
  }

  @Test
  public void shouldSaveEntity() {
    Thing thing = Thing.builder().name("thingName").build();

    thingRepository.save(thing);

    assertThat(thing.getId()).isNotNull();
    assertThat(thing.getId()).isGreaterThan(0);
  }
}
