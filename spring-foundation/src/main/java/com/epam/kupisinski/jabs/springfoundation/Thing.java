package com.epam.kupisinski.jabs.springfoundation;

import javax.persistence.*;

@Entity
public class Thing {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = true, unique = true)
  private String name;
}
