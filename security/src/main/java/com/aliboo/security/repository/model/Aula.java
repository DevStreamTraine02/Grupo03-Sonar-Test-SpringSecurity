package com.aliboo.security.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "AULA")
public class Aula {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IdAula", nullable = false)
  private Long id;

  @Column(name = "Nombre", length = Integer.MAX_VALUE)
  private String nombre;

  @Column(name = "Capacidad", length = Integer.MAX_VALUE)
  private String capacidad;

  @Column(name = "Ubicacion", length = Integer.MAX_VALUE)
  private String ubicacion;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "IdGradoNivel", nullable = false)
  private GradoNivel idGradoNivel;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "IdSeccion", nullable = false)
  private Seccion idSeccion;

  @OneToMany(mappedBy = "idAula")
  private Set<Clase> clases = new LinkedHashSet<>();

}