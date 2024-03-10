package com.aliboo.security.repository;

import com.aliboo.security.repository.model.Profesor;
import com.aliboo.security.repository.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TeacherRepository extends JpaRepository<Profesor, Long> {
  Optional<Profesor> findByIdUsuario(Usuario usuario);
}
