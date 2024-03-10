package com.aliboo.security.controller.advice;
public class BuilderCodigoError {

  private BuilderCodigoError() {
  }

  public static String returnCodigoError(String tipoError, String tipoCanal, String componente, String numeroError) {
    return tipoError + tipoCanal + "-" + componente + "-" + numeroError;
  }
}
