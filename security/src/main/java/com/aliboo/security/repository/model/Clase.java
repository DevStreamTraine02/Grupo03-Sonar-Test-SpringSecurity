package com.aliboo.security.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CLASE")
public class Clase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IdClase", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "IdCurso", nullable = false)
  private Curso idCurso;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "IdAula", nullable = false)
  private Aula idAula;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "IdProfesor", nullable = false)
  private Profesor idProfesor;

  @Column(name = "Horas")
  private Long horas;

  @OneToMany(mappedBy = "idClase")
  private Set<DetalleClase> detalleClases = new LinkedHashSet<>();

}