package com.vns.his.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Thiết lập server
                .servers(Lists.newArrayList(
                        new Server().url("/common/").description("common info Server URL")
                ))
                // info
                .info(new Info().title("VINORSOFT HIS APIs")
                                .description("common-info-service APIs for web"));
    }
}