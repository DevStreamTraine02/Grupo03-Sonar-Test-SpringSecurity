package com.aliboo.security.controller.wrapper;


import lombok.*;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WrapperGenericoLista<T> implements Serializable {

  @Valid
  private transient List<T> data;
}