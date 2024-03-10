package com.aliboo.security.controller.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse {
  private TipoError error;
}