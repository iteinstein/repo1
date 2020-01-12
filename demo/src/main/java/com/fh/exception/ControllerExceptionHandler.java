package com.fh.exception;

import com.fh.common.ServerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ServerResponse handlerException(GlobalException e){
        return ServerResponse.error(e.getResponseEnum());
    }

    @ExceptionHandler(Exception.class)
    public ServerResponse handlerException(Exception e){
        return ServerResponse.error();
    }

}
