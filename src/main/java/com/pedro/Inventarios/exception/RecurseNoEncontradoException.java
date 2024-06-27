package com.pedro.Inventarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecurseNoEncontradoException extends  RuntimeException{
    public RecurseNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
