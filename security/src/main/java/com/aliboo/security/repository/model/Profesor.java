package com.aliboo.security.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROFESOR")
public class Profesor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IdProfesor", nullable = false)
  private Long id;

  @Column(name = "Correo", length = Integer.MAX_VALUE)
  private String correo;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "IdUsuario", nullable = false)
  private Usuario idUsuario;

  @OneToMany(mappedBy = "idProfesor")
  private Set<Clase> clases = new LinkedHashSet<>();

}