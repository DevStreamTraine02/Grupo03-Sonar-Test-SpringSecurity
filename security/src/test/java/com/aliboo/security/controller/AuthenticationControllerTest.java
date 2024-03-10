package com.aliboo.security.controller;

import com.aliboo.security.controller.request.StudentRequest;
import com.aliboo.security.controller.request.TeacherRequest;
import com.aliboo.security.controller.request.UserRequest;
import com.aliboo.security.controller.enums.UserTypeEnum;
import com.aliboo.security.controller.response.AuthenticationResponse;
import com.aliboo.security.controller.response.StudentResponse;
import com.aliboo.security.controller.response.TeacherResponse;
import com.aliboo.security.controller.response.UserResponse;
import com.aliboo.security.controller.wrapper.WrapperGenericoObject;
import com.aliboo.security.repository.model.Rol;
import com.aliboo.security.repository.model.Usuario;
import com.aliboo.security.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {


  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthenticationService authenticationService;

  @Autowired
  private ObjectMapper objectMapper;

  private AuthenticationResponse authenticationResponseStudent;
  private AuthenticationResponse authenticationResponseTeacher;

  @BeforeEach
  void setUp() {
    /*rol = Rol.builder()
      .id(1L)
      .nombre("ALUMNO")
      .descripcion("Rol generico")
      .build();
    usuario.getRoles().add(rol);
    rol.getUsuarios().add(usuario);*/
    authenticationResponseStudent = AuthenticationResponse.builder()
      .token("testToken")
      .user(UserResponse.builder()
        .id(1L)
        .username("testUser")
        .name("Test")
        .lastname("User")
        .roles(Arrays.asList("ALUMNO"))
        .build()
      )
      .student(StudentResponse.builder()
        .id(1L)
        .address("123 Street")
        .birthday(LocalDate.of(2000, 1, 1))
        .build()
      )
      .build();
     authenticationResponseTeacher = AuthenticationResponse.builder()
      .token("testToken")
      .user(UserResponse.builder()
        .id(1L)
        .username("testUser")
        .name("Test")
        .lastname("User")
        .roles(Arrays.asList("PROFESOR"))
        .build())
      .teacher(TeacherResponse.builder()
        .id(1L)
        .email("test@example.com")
        .build())
      .build();

  }

  @Test
  void registerUserTest() throws Exception {
    UserRequest userRequest = UserRequest.builder()
      .username("testUser")
      .name("Test")
      .lastname("User")
      .password("testPassword")
      .userType(UserTypeEnum.PROFESOR)
      .studentRequest(StudentRequest.builder().build())
      .build();

    when(authenticationService.register(any(UserRequest.class))).thenReturn(authenticationResponseStudent);

    mockMvc.perform(post("/api/v1/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(WrapperGenericoObject.<UserRequest>builder().data(userRequest).build())))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.token").value(authenticationResponseStudent.getToken()))
      .andExpect(jsonPath("$.data.user.id").value(authenticationResponseStudent.getUser().getId()))
      .andExpect(jsonPath("$.data.user.username").value(authenticationResponseStudent.getUser().getUsername()))
      .andExpect(jsonPath("$.data.user.name").value(authenticationResponseStudent.getUser().getName()))
      .andExpect(jsonPath("$.data.user.lastname").value(authenticationResponseStudent.getUser().getLastname()))
      .andExpect(jsonPath("$.data.user.roles[0]").value(authenticationResponseStudent.getUser().getRoles().get(0)))
      .andExpect(jsonPath("$.data.student.id").value(authenticationResponseStudent.getStudent().getId()))
      .andExpect(jsonPath("$.data.student.address").value(authenticationResponseStudent.getStudent().getAddress()))
      .andExpect(jsonPath("$.data.student.birthday").value(authenticationResponseStudent.getStudent().getBirthday().toString()));
    verify(authenticationService, times(1)).register(userRequest);
  }
  @Test
  public void testRegisterTeacher() throws Exception {
    UserRequest userRequest = UserRequest.builder()
      .username("testUser")
      .name("Test")
      .lastname("User")
      .password("testPassword")
      .userType(UserTypeEnum.PROFESOR)
      .teacherRequest(TeacherRequest.builder().email("test@example.com").build())
      .build();


    when(authenticationService.register(userRequest)).thenReturn(authenticationResponseTeacher);

    mockMvc.perform(post("/api/v1/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(WrapperGenericoObject.<UserRequest>builder().data(userRequest).build())))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.token").value(authenticationResponseTeacher.getToken()))
      .andExpect(jsonPath("$.data.user.id").value(authenticationResponseTeacher.getUser().getId()))
      .andExpect(jsonPath("$.data.user.username").value(authenticationResponseTeacher.getUser().getUsername()))
      .andExpect(jsonPath("$.data.user.name").value(authenticationResponseTeacher.getUser().getName()))
      .andExpect(jsonPath("$.data.user.lastname").value(authenticationResponseTeacher.getUser().getLastname()))
      .andExpect(jsonPath("$.data.user.roles[0]").value(authenticationResponseTeacher.getUser().getRoles().get(0)))
      .andExpect(jsonPath("$.data.teacher.id").value(authenticationResponseTeacher.getTeacher().getId()))
      .andExpect(jsonPath("$.data.teacher.email").value(authenticationResponseTeacher.getTeacher().getEmail()));

    verify(authenticationService, times(1)).register(userRequest);
  }

}
