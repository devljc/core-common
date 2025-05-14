package com.example.core.response;

import lombok.Getter;

import java.nio.charset.StandardCharsets;

@Getter
public class ApiResponse<T> {

    private final boolean success;
    private final T data;
    private final ErrorResponse error;

    private ApiResponse(boolean success, T data, ErrorResponse error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> error(ErrorResponse errorResponse) {
        return new ApiResponse<>(false, null, errorResponse);
    }

    public static byte[] errorJsonBytes(int status, String message) {
        return """
                    {
                        "success": false,
                        "data": null,
                        "error": {
                            "status": %d,
                            "message": "%s"
                        }
                    }
                """
                .formatted(status, message)
                .getBytes(StandardCharsets.UTF_8);
    }
}