package com.fulgay.bilenyum.dtos;

import com.fulgay.bilenyum.commons.ErrorDto;

public class BaseDto {
    private String message;
    private ErrorDto errorDto;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorDto getErrorDto() {
        return errorDto;
    }

    public void setErrorDto(ErrorDto errorDto) {
        this.errorDto = errorDto;
    }
}
