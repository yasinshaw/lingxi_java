package com.lingxi.lingxi_java.utils;

import com.auth0.jwt.interfaces.Claim;
import com.lingxi.lingxi_java.common.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JwtUtilTest {

    @Test
    void getJwtToken() {
        String token = JwtUtil.generateByUserId(1L);
        System.out.println(Constants.AUTH_PREFIX + token);
        Map<String, Claim> resolve = JwtUtil.resolve(token);
        Assertions.assertEquals(resolve.get("user_id").asLong(), 1L);
    }

}