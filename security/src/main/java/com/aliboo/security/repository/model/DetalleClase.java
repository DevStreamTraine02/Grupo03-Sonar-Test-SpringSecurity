package com.aliboo.security.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DETALLE_CLASE")
public class DetalleClase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IdDetalleClase", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "IdEstudiante", nullable = false)
  private Alumno idEstudiante;

  @Column(name = "Nota")
  private Long nota;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IdClase")
  private Clase idClase;

}