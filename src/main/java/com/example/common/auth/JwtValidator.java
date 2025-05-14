package com.example.common.auth;

import com.example.common.exception.CustomException;
import com.example.common.exception.ErrorCode;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtValidator {

    private final ConfigurableJWTProcessor<SecurityContext> jwtProcessor;

    public JWTClaimsSet validate(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return jwtProcessor.process(signedJWT, null);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.UNAUTHORIZED, "JWT invalid: " + e.getMessage());
        }
    }

    public boolean isValid(String token) {
        try {
            return validate(token) != null;
        } catch (CustomException e) {
            return false;
        }
    }
}
