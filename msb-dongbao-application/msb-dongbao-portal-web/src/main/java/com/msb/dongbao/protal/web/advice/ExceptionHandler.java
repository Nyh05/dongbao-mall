package com.msb.dongbao.protal.web.advice;

import com.msb.dongbao.common.base.result.ResultWrapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*@ControllerAdvice
@RestController*/
//上述两个可以用restcontrollerAdvice来代替
@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ArithmeticException.class)
    public ResultWrapper customException(){
        return ResultWrapper.builder().code(301).message("统一异常处理").build();
    }
}
