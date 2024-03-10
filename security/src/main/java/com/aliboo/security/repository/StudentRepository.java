package com.aliboo.security.repository;

import com.aliboo.security.repository.model.Alumno;
import com.aliboo.security.repository.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Alumno, Long> {
  Optional<Alumno> findByIdUsuario(Usuario usuario);
}
