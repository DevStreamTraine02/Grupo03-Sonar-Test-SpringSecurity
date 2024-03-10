package com.aliboo.security.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ROL")
public class Rol {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IdRol", nullable = false)
  private Long id;

  @Column(name = "Nombre", length = Integer.MAX_VALUE)
  private String nombre;

  @Column(name = "Descripcion", length = Integer.MAX_VALUE)
  private String descripcion;

  @ManyToMany(mappedBy = "roles")
  private Set<Usuario> usuarios = new HashSet();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Rol rol = (Rol) o;
    return Objects.equals(nombre, rol.nombre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre);
  }
}
