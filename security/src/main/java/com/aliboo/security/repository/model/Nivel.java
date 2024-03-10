package com.aliboo.security.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "NIVEL")
public class Nivel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IdNivel", nullable = false)
  private Long id;

  @Column(name = "Nivel", length = Integer.MAX_VALUE)
  private String nivel;

  @OneToMany(mappedBy = "idNivel")
  private Set<GradoNivel> gradoNivels = new LinkedHashSet<>();

}