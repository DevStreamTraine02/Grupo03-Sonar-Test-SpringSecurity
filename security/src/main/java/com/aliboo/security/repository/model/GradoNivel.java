package com.aliboo.security.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "GRADO_NIVEL")
public class GradoNivel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IdGradoNivel", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "IdGrado", nullable = false)
  private Grado idGrado;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "IdNivel", nullable = false)
  private Nivel idNivel;

  @OneToMany(mappedBy = "idGradoNivel")
  private Set<Aula> aulas = new LinkedHashSet<>();

}