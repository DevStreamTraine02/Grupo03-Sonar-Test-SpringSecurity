package com.aliboo.security.controller.wrapper;


import jakarta.validation.Valid;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WrapperGenericoObject<T> implements Serializable {

  @Valid
  private transient T data;
}