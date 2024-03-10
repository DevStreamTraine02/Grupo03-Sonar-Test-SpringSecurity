package com.aliboo.security.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CURSO")
public class Curso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IdCurso", nullable = false)
  private Long id;

  @Column(name = "NombreCurso", length = Integer.MAX_VALUE)
  private String nombreCurso;

  @Column(name = "Descripcion", length = Integer.MAX_VALUE)
  private String descripcion;

  @OneToMany(mappedBy = "idCurso")
  private Set<Clase> clases = new LinkedHashSet<>();

}