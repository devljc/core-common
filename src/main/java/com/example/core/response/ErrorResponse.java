package com.example.core.response;

import com.example.core.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String message;

    private ErrorResponse(ErrorCode code) {
        this.status = code.getStatus();
        this.message = code.getMessage();
    }
    private ErrorResponse(ErrorCode code, String customMessage) {
        this.status = code.getStatus();
        this.message = customMessage;
    }

    private ErrorResponse(int status, String customMessage) {
        this.status = status;
        this.message = customMessage;
    }

    private static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(ErrorCode code, String customMessage) {
        return new ErrorResponse(code, customMessage);
    }

    public static ErrorResponse of(int status, String customMessage) {
        return new ErrorResponse(status, customMessage);
    }

}