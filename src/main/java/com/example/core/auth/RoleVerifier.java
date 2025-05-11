package com.example.core.auth;

import com.example.core.exception.CustomException;
import com.example.core.exception.ErrorCode;

public class RoleVerifier {

    public static void require(String actual, String required) {
        if (actual == null || !actual.equals(required)) throw new CustomException(ErrorCode.FORBIDDEN);
    }
}
