package com.example.common.auth;

import com.example.common.exception.CustomException;
import com.example.common.exception.ErrorCode;

public class RoleVerifier {

    public static void require(String actual, String required) {
        if (actual == null || !actual.equals(required)) throw new CustomException(ErrorCode.FORBIDDEN);
    }
}
