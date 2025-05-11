package com.example.core.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_REQUEST(400, "Invalid request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Access denied: insufficient permissions"),
    NOT_FOUND(404, "Resource not found"),
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}