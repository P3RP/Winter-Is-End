package com.example.week2.exception;

import org.json.simple.JSONObject;

public class ErrorResponse {
    public static JSONObject JsonErrorResponse(int errorCode, String errorMessage){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errorCode", errorCode);
        jsonObject.put("errorMessage", errorMessage);
        return jsonObject;
    }
}
//JSON으로 에러를 리턴하기 위해 JSON-simple이라는 의존성을 pom.xml에 가져옴
