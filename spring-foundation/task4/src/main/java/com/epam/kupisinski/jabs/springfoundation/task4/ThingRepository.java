package com.epam.kupisinski.jabs.springfoundation.task4;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ThingRepository extends JpaRepository<Thing, Long> {}
