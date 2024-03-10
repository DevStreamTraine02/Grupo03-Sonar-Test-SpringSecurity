package com.aliboo.security.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALUMNO")
public class Alumno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IdEstudiante", nullable = false)
  private Long id;

  @Column(name = "Direccion", length = Integer.MAX_VALUE)
  private String direccion;

  @Column(name = "Fecha de Nacimiento")
  private LocalDate fechaDeNacimiento;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "IdUsuario", nullable = false)
  private Usuario idUsuario;

  @OneToMany(mappedBy = "idEstudiante")
  private Set<DetalleClase> detalleClases = new LinkedHashSet<>();

}