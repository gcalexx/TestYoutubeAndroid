package com.example.testyoutube.model;

public class ResponseError {

    private Integer code;
    private String message;
    private Integer httpCode;
    private String type;

    public ResponseError() {
    }

    public ResponseError(Integer code, String message, Integer httpCode, String type) {
        this.code = code;
        this.message = message;
        this.httpCode = httpCode;
        this.type = type;
    }

    public ResponseError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseError(String message, Integer httpCode) {
        this.message = message;
        this.httpCode = httpCode;
    }

    public ResponseError(String message) {
        this.message = message;
        this.code = 500;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
