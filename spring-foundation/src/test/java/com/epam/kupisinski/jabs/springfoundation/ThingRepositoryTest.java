package com.epam.kupisinski.jabs.springfoundation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
class ThingRepositoryTest {

  @Autowired private ThingRepository repositoryUnderTest;

  @Test
  public void shouldSaveThingToDb() {
    Thing theThing = Thing.builder().name("thingName").build();

    repositoryUnderTest.save(theThing);

    assertThat(theThing.getId()).isNotNull();
  }
}
