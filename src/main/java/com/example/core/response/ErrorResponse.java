package com.example.core.response;

import com.example.core.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String message;

    public ErrorResponse(ErrorCode code) {
        this.status = code.getStatus();
        this.message = code.getMessage();
    }

    public ErrorResponse(int status, String customMessage) {
        this.status = status;
        this.message = customMessage;
    }

}