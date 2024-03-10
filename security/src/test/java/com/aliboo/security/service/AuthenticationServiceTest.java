package com.aliboo.security.service;

import com.aliboo.security.controller.enums.UserTypeEnum;
import com.aliboo.security.controller.request.AuthenticationRequest;
import com.aliboo.security.controller.request.UserRequest;
import com.aliboo.security.controller.response.AuthenticationResponse;
import com.aliboo.security.repository.RolRepository;
import com.aliboo.security.repository.StudentRepository;
import com.aliboo.security.repository.TeacherRepository;
import com.aliboo.security.repository.UserRepository;
import com.aliboo.security.repository.model.Alumno;
import com.aliboo.security.repository.model.Usuario;
import com.aliboo.security.service.exception.UserAlreadyTakenException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private StudentRepository studentRepository;

  @Mock
  private TeacherRepository teacherRepository;

  @Mock
  private RolRepository rolRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private JwtService jwtService;

  @Mock
  private AuthenticationManager authenticationManager;

  @InjectMocks
  private AuthenticationService authenticationService;

  @Test
  void testRegisterWithExistingUser() {
    UserRequest request = new UserRequest();
    request.setUsername("existing_user");
    when(userRepository.findByNombreUsuario("existing_user")).thenReturn(Optional.of(new Usuario()));

    assertThrows(UserAlreadyTakenException.class, () -> authenticationService.register(request));
  }

  @Test
  void testRegisterStudent() {
    UserRequest request = new UserRequest();
    request.setUsername("new_user");
    request.setUserType(UserTypeEnum.ALUMNO);
    when(userRepository.findByNombreUsuario("new_user")).thenReturn(Optional.empty());
    when(userRepository.save(any(Usuario.class))).thenReturn(new Usuario());
    when(jwtService.generateToken(any(Usuario.class))).thenReturn("token");
    when(studentRepository.save(any(Alumno.class))).thenReturn(new Alumno());

    AuthenticationResponse response = authenticationService.register(request);

    assertNotNull(response);
    assertNotNull(response.getToken());
    assertNotNull(response.getStudent());
  }

  @Test
  void testAuthenticateWithInvalidCredentials() {
    AuthenticationRequest request = new AuthenticationRequest();
    request.setUsername("username");
    request.setPassword("wrong_password");
    when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Invalid credentials"));

    assertThrows(BadCredentialsException.class, () -> authenticationService.authenticate(request));
  }
}
