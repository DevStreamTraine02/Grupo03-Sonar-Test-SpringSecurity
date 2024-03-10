package com.aliboo.security.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
  private String token;
  private UserResponse user;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private StudentResponse student;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private TeacherResponse teacher;
}
