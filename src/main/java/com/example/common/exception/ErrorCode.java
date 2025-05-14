package com.example.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common client/server errors
    INVALID_REQUEST(400, "The request is invalid."),
    UNAUTHORIZED(401, "Authentication is required."),
    FORBIDDEN(403, "You do not have permission to access this resource."),
    NOT_FOUND(404, "The requested resource was not found."),
    INTERNAL_SERVER_ERROR(500, "An unexpected error occurred on the server."),
    TOO_MANY_REQUESTS(429, "Too many requests. Please try again later."),

    // OAuth / External API related
    INVALID_OAUTH_TOKEN(401, "The external OAuth token is invalid or has expired."),
    EXTERNAL_AUTH_FAILURE(502, "Failed to communicate with the external authentication provider.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorCode fromStatus(int status) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.getStatus() == status) {
                return errorCode;
            }
        }
        return INTERNAL_SERVER_ERROR;
    }
}