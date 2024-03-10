package com.aliboo.security.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
  private Long id;
  private String address;
  private LocalDate birthday;
}
