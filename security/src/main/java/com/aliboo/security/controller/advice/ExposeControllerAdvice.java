package com.aliboo.security.controller.advice;

import com.aliboo.security.controller.response.ErrorResponse;
import com.aliboo.security.controller.response.TipoError;
import com.aliboo.security.service.exception.UserAlreadyTakenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExposeControllerAdvice {

  @ExceptionHandler(value = {UserAlreadyTakenException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleLimiteCantidadDigitosCuentaException(Exception exception) {

    log.error(this.getClass().getSimpleName(), exception);

    String code = BuilderCodigoError.returnCodigoError(
      DetalleCatalogoError.FUNCTIONAL_PREFIX_ERROR,
      DetalleCatalogoError.NEGOCIO_CHANNEL_PREFIX_ERROR,
      DetalleCatalogoError.EXTERNO_COMPONENT_PREFIX_ERROR,
      DetalleErrorEnum.USER_ALREADY_TAKEN.getCode());

    return buildErrorResponse(TipoError.TipoEnum.FUNCIONAL, code, exception.getMessage());
  }

  private ErrorResponse buildErrorResponse(TipoError.TipoEnum tipo, String code, String message) {
    return ErrorResponse.builder()
      .error(TipoError.builder()
        .tipo(tipo)
        .codigo(code)
        .mensaje(message)
        .build()
      ).build();
  }
}