package com.aliboo.security.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DetalleErrorEnum {
  HEADER_FORMAT_ERROR("001"),
  HEADER_VALIDATE_ERROR("002"),
  REQUEST_BODY_FORMAT_ERROR("003"),
  REQUEST_BODY_VALIDATE_ERROR("004"),
  USER_ALREADY_TAKEN("005");

  private final String code;
}
