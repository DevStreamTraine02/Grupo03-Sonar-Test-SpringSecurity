package com.aliboo.security.repository.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USUARIO")
public class Usuario implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IdUsuario", nullable = false)
  private Long id;

  @Column(name = "NombreUsuario", length = Integer.MAX_VALUE)
  private String nombreUsuario;

  @Column(name = "Contraseña", length = Integer.MAX_VALUE)
  private String contraseña;

  @Column(name = "Nombre", length = Integer.MAX_VALUE)
  private String nombre;

  @Column(name = "Apellidos", length = Integer.MAX_VALUE)
  private String apellidos;

  @ManyToMany(fetch = FetchType.LAZY )
  @JoinTable(name = "UsuarioRol",  // Nombre de la tabla existente en la base de datos
    joinColumns = @JoinColumn(name = "IdUsuario"),
    inverseJoinColumns = @JoinColumn(name = "IdRol"))
  private Set<Rol> roles = new HashSet<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.roles == null) {
      this.roles = new HashSet<>(); // or another Set implementation
    }
    return roles.stream()
      .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
      .collect(Collectors.toList());
  }
  @Override
  public String getPassword() {
    return this.contraseña;
  }

  @Override
  public String getUsername() {
    return this.nombreUsuario;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Usuario user = (Usuario) o;
    return Objects.equals(nombre, user.getNombreUsuario());
  }
}
