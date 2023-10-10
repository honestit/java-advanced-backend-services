package com.epam.kupisinski.jabs.springfoundation;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

class DataSourceProfileBasedConfigurationTest {

  @Nested
  @ExtendWith(SpringExtension.class)
  @ActiveProfiles(value = "QA")
  @Component
  @Import({JpaRepositoriesAutoConfiguration.class, DataSourceProfileBasedConfiguration.class})
  class QaProfileTest {

    @Autowired private ThingRepository thingRepository;

    @Test
    public void sanityCheck() {}
  }

  @Nested
  @SpringBootTest
  @ActiveProfiles("DEV")
  @Import({JpaRepositoriesAutoConfiguration.class, DataSourceProfileBasedConfiguration.class})
  class DevProfileTest {

    @Autowired private ThingRepository thingRepository;

    @Test
    public void sanityCheck() {}
  }
}
