package com.lingxi.lingxi_java.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

@Configuration
public class JwtUtil {

    private static final long TIMEOUT = 3600 * 1000 * 24; // 过期时间一天

    private static String secretKey;
    private static Algorithm algorithm;
    private static final String USER_ID = "user_id";

    @Value("${custom.jwt-secret}")
    public void setSecretKey(String secretKey) {
        JwtUtil.secretKey = secretKey;
        algorithm = Algorithm.HMAC256(JwtUtil.secretKey);
    }

    public static String generateByUserId(Long id) {
        long now = System.currentTimeMillis();
        return JWT.create()
                .withJWTId(id.toString())
                .withSubject(id.toString())
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + TIMEOUT))
                .withClaim(USER_ID, id)
                .sign(algorithm);
    }

    public static Map<String, Claim> resolve(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims();
    }

    public static Long getUserIdByToken(String token) {
        return resolve(token).get(USER_ID).asLong();
    }
}
