package com.aliboo.security.controller.request;

import com.aliboo.security.controller.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
  private String username;
  private String name;
  private String lastname;
  private String password;
  private UserTypeEnum userType;
  private StudentRequest studentRequest;
  private TeacherRequest teacherRequest;
}
