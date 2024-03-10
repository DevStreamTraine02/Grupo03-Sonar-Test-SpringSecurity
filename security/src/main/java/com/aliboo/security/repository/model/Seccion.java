package com.aliboo.security.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "SECCION")
public class Seccion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IdSeccion", nullable = false)
  private Long id;

  @Column(name = "NombreSeccion", length = Integer.MAX_VALUE)
  private String nombreSeccion;

  @OneToMany(mappedBy = "idSeccion")
  private Set<Aula> aulas = new LinkedHashSet<>();

}