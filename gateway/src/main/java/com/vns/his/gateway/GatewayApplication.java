package com.vns.his.gateway;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.vns.his.gateway.security.JwtConfig;

@SpringBootApplication
@EnableEurekaClient 
@EnableZuulProxy 
public class GatewayApplication {

	@Bean
	public JwtConfig jwtConfig() {
	  return new JwtConfig();
	}
  
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
          @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**" )
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT","DELETE");
            }
        };
    }


    @Bean
    public GroupedOpenApi authApi() {
      return GroupedOpenApi.builder()
              .group("auth")
              .pathsToMatch("/auth/**")
              .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
      return GroupedOpenApi.builder()
              .group("user")
              .pathsToMatch("/user/**")
              .build();
    }

    @Bean
    public GroupedOpenApi commonApi() {
      return GroupedOpenApi.builder()
              .group("common")
              .pathsToMatch("/common/**")
              .build();
    }
}
