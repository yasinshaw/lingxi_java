package com.lingxi.lingxi_java.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySwaggerConfig {
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("Admin")
                // 指定路径
                .pathsToMatch("/admin/**")
                // 指定特定的 API 文档信息
                .addOpenApiCustomizer(userApiCustomizer())
                .build();
    }

    /**
     * 定义 OpenApiCustomiser ，用于指定的 group
     * @return
     */
    public OpenApiCustomizer userApiCustomizer() {
        return openApi -> openApi.info(new Info()
                .title("用户管理 API 文档")
                .description("实现对用户数据的增删改查等操作")
                .version("1.0")
                .contact(new io.swagger.v3.oas.models.info.Contact().name("yasinshaw").email("yasinshaw@gmail.com")));
    }
}
