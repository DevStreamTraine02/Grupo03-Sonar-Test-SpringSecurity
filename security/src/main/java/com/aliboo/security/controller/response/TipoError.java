package com.aliboo.security.controller.response;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class TipoError {

  public enum TipoEnum {

    FUNCIONAL("FUNCIONAL"),
    SISTEMA("SISTEMA");
    private final String value;

    TipoEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }

  }

  private TipoEnum tipo;
  private String codigo;
  private String mensaje;

}