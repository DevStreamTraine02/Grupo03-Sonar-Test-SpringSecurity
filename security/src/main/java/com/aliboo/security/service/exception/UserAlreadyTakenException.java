package com.aliboo.security.service.exception;


public class UserAlreadyTakenException extends RuntimeException {

  public UserAlreadyTakenException(String message) {
    super("User Name alreafy taken " + message);
  }
}
