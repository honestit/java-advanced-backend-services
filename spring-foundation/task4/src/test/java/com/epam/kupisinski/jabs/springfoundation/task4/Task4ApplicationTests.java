package com.epam.kupisinski.jabs.springfoundation.task4;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;

import static com.google.common.truth.Truth.assertThat;

class Task4ApplicationTests {

  @Nested
  @SpringBootTest
  class DefaultProfileTest {

    @Autowired private DataSource dataSource;

    @Autowired private ThingRepository thingRepository;

    @Test
    public void shouldUseDefaultDataSource() throws Exception {
      DatabaseMetaData metaData = dataSource.getConnection().getMetaData();

      assertThat(metaData.getURL()).doesNotMatch(".*spring-foundations.*");
    }

    @Test
    public void shouldSaveEntity() {
      Thing thing = Thing.builder().name("thingName").build();

      thingRepository.save(thing);

      assertThat(thing.getId()).isNotNull();
      assertThat(thing.getId()).isGreaterThan(0);
    }
  }

  @Nested
  @SpringBootTest
  @ActiveProfiles("QA")
  class QAProfileTest {

    @Autowired private DataSource dataSource;

    @Autowired private ThingRepository thingRepository;

    @Test
    public void shouldUseDefaultDataSource() throws Exception {
      DatabaseMetaData metaData = dataSource.getConnection().getMetaData();

      assertThat(metaData.getURL()).startsWith("jdbc:h2:mem:spring-foundations-qa");
    }

    @Test
    public void shouldSaveEntity() {
      Thing thing = Thing.builder().name("thingName").build();

      thingRepository.save(thing);

      assertThat(thing.getId()).isNotNull();
      assertThat(thing.getId()).isGreaterThan(0);
    }
  }

  @Nested
  @SpringBootTest
  @ActiveProfiles("DEV")
  class DevProfileTest {

    @Autowired private DataSource dataSource;

    @Autowired private ThingRepository thingRepository;

    @Test
    public void shouldUseDefaultDataSource() throws Exception {
      DatabaseMetaData metaData = dataSource.getConnection().getMetaData();

      assertThat(metaData.getURL()).startsWith("jdbc:h2:mem:spring-foundations-dev");
    }

    @Test
    public void shouldSaveEntity() {
      Thing thing = Thing.builder().name("thingName").build();

      thingRepository.save(thing);

      assertThat(thing.getId()).isNotNull();
      assertThat(thing.getId()).isGreaterThan(0);
    }
  }
}
