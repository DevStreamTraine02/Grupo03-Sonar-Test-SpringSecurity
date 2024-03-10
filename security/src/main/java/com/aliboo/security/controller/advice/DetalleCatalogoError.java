package com.aliboo.security.controller.advice;

public final class DetalleCatalogoError {

  private DetalleCatalogoError() {
  }

  public static final String SYSTEM_PREFIX_ERROR = "S";
  public static final String FUNCTIONAL_PREFIX_ERROR = "F";

  public static final String NEGOCIO_CHANNEL_PREFIX_ERROR = "NE";
  public static final String GENERICO_CHANNEL_PREFIX_ERROR = "GE";

  public static final String MICROSERVICIO_COMPONENT_PREFIX_ERROR = "MS";
  public static final String EXTERNO_COMPONENT_PREFIX_ERROR = "SE";
  public static final String DATABASE_COMPONENT_PREFIX_ERROR = "BD";
}