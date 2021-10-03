package com.example.week2.exception;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice   //@Controller에서 발생할 수 있는 예외를 잡아 처리해주는 annotation임
@ResponseBody
public class GlobalException {
    @ExceptionHandler(DuplicateEmailException.class)
    public JSONObject duplicateEmailException(){
        return ErrorResponse.JsonErrorResponse(400,"중복된 이메일");
    }
}
