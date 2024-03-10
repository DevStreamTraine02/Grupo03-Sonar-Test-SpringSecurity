package com.aliboo.security.repository;

import com.aliboo.security.repository.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
  Optional<Rol> findByNombre(String name);
}