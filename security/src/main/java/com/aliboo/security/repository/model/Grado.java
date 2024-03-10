package com.aliboo.security.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "GRADO")
public class Grado {
  @Id
  @Column(name = "IdGrado", nullable = false)
  private Long id;

  @Column(name = "NumGrado", length = Integer.MAX_VALUE)
  private String numGrado;

  @OneToMany(mappedBy = "idGrado")
  private Set<GradoNivel> gradoNivels = new LinkedHashSet<>();

}