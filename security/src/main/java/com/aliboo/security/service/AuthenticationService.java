package com.aliboo.security.service;

import com.aliboo.security.controller.enums.UserTypeEnum;
import com.aliboo.security.controller.request.AuthenticationRequest;
import com.aliboo.security.controller.response.AuthenticationResponse;
import com.aliboo.security.controller.response.StudentResponse;
import com.aliboo.security.controller.request.UserRequest;
import com.aliboo.security.controller.response.TeacherResponse;
import com.aliboo.security.controller.response.UserResponse;
import com.aliboo.security.repository.model.Alumno;
import com.aliboo.security.repository.model.Profesor;
import com.aliboo.security.repository.model.Usuario;
import com.aliboo.security.repository.model.Rol;
import com.aliboo.security.repository.RolRepository;
import com.aliboo.security.repository.StudentRepository;
import com.aliboo.security.repository.TeacherRepository;
import com.aliboo.security.repository.UserRepository;
import com.aliboo.security.service.exception.UserAlreadyTakenException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;
  private final RolRepository rolRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  @Transactional
  public AuthenticationResponse register(UserRequest request) {
    var existingUser  = userRepository.findByNombreUsuario(request.getUsername());
    if(existingUser.isPresent()){
      throw new UserAlreadyTakenException(request.getUsername());
    }
    var user = Usuario.builder()
      .nombreUsuario(request.getUsername())
      .nombre(request.getName())
      .apellidos(request.getLastname())
      .contraseña(passwordEncoder.encode(request.getPassword()))
      .roles(toEntityRol(request.getUserType()))
      .build();
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var userResponse = toAuthenticationResponse(jwtToken, user);
    if (request.getUserType() == UserTypeEnum.ALUMNO){
      var student = Alumno.builder()
        .fechaDeNacimiento(request.getStudentRequest().getBirthday())
        .direccion(request.getStudentRequest().getAddress())
        .idUsuario(user)
        .build();
      studentRepository.save(student);
      userResponse.setStudent(toStudentResponse(student));
    }else if(request.getUserType() == UserTypeEnum.PROFESOR){
      var teacher = Profesor.builder()
        .correo(request.getTeacherRequest().getEmail())
        .idUsuario(user)
        .build();
      teacherRepository.save(teacher);
      userResponse.setTeacher(toTeacherResponse(teacher));
    }
    return userResponse;
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getUsername(),
        request.getPassword()
      )
    );
    var user = userRepository.findByNombreUsuario(request.getUsername())
      .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var userResponse = toAuthenticationResponse(jwtToken, user);
    if (askForRole(user,"PROFESOR")) {
      var teacher = teacherRepository.findByIdUsuario(user).orElseThrow();
      userResponse.setTeacher(toTeacherResponse(teacher));
    } else if (askForRole(user,"ALUMNO")) {
      var student = studentRepository.findByIdUsuario(user).orElseThrow();
      userResponse.setStudent(toStudentResponse(student));
    }
    return userResponse;
  }
  private AuthenticationResponse toAuthenticationResponse(String jwtToken, Usuario user){
    return AuthenticationResponse.builder()
      .token(jwtToken)
      .user(toUserReponse(user))
      .build();
  }
  private StudentResponse toStudentResponse( Alumno student){
    return StudentResponse.builder()
      .id(student.getId())
      .address(student.getDireccion())
      .birthday(student.getFechaDeNacimiento())
      .build();
  }
  private TeacherResponse toTeacherResponse(Profesor teacher){
    return TeacherResponse.builder()
      .id(teacher.getId())
      .email(teacher.getCorreo())
      .build();
  }
  private UserResponse toUserReponse(Usuario user){
    return UserResponse.builder()
      .id(user.getId())
      .username(user.getUsername())
      .name(user.getNombre())
      .lastname(user.getApellidos())
      .roles(user.getRoles().stream()
        .map(Rol::getNombre)
        .collect(Collectors.toList()))
      .build();
  }
  private Set<Rol> toEntityRol(UserTypeEnum userType) {
    Set<Rol> roles = new HashSet<>();
    if (userType == UserTypeEnum.ALUMNO) {
      Rol alumnoRol = rolRepository.findByNombre("ALUMNO")
        .orElseThrow();
      roles.add(alumnoRol);
    } else if (userType == UserTypeEnum.PROFESOR) {
      Rol profesorRol = rolRepository.findByNombre("PROFESOR")
        .orElseThrow();
      roles.add(profesorRol);
    }
    return roles;
  }
  private boolean askForRole(Usuario user, String rol){
    var askedRol = Rol.builder()
      .nombre(rol)
      .build();
    return user.getRoles().contains(askedRol);
  }
}
