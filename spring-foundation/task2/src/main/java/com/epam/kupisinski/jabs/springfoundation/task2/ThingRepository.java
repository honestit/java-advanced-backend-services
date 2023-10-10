package com.epam.kupisinski.jabs.springfoundation.task2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ThingRepository extends JpaRepository<Thing, Long> {}
