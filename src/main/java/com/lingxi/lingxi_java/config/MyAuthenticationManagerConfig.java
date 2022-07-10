package com.lingxi.lingxi_java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class MyAuthenticationManagerConfig {
    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> authentication;
    }
}
