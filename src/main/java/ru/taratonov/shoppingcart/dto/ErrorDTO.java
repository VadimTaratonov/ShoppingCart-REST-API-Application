package ru.taratonov.shoppingcart.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDTO {
    private String msg;
    private LocalDateTime errorTime;
    private HttpStatus httpStatus;

    public ErrorDTO(String msg, LocalDateTime errorTime, HttpStatus httpStatus) {
        this.msg = msg;
        this.errorTime = errorTime;
        this.httpStatus = httpStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(LocalDateTime errorTime) {
        this.errorTime = errorTime;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
