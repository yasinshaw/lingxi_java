package com.lingxi.lingxi_java.config;

import com.lingxi.lingxi_java.utils.AuthenticationUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "dateTimeProvider")
public class MyJpaAuditingConfiguration {

    @Bean
    public DateTimeProvider dateTimeProvider() {
        DateTimeProvider dateTimeProvider = () -> Optional.of(new Date().toInstant());
        return dateTimeProvider;
    }

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return () -> Optional.ofNullable(AuthenticationUtil.getCurrentUserId());
    }
}
