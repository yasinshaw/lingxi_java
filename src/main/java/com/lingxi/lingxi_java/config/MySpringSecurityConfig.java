package com.lingxi.lingxi_java.config;

import com.lingxi.lingxi_java.common.filters.JWTAuthenticationFilter;
import com.lingxi.lingxi_java.common.handlers.AuthAccessDeniedHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import static com.lingxi.lingxi_java.common.Constants.AUTH_WHITELIST;

@Configuration
@EnableWebSecurity
public class MySpringSecurityConfig {

    @Resource
    private AuthAccessDeniedHandler authAccessDeniedHandler;
    @Resource
    private JWTAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf() // 使用jwt，关闭csrf
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 不需要session
                .and()
                .authorizeHttpRequests((auth) -> auth
                        .antMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter, LogoutFilter.class)
                .httpBasic()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(authAccessDeniedHandler)
        ;

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(AUTH_WHITELIST);
    }

}
