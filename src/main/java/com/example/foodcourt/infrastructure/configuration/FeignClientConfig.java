package com.example.foodcourt.infrastructure.configuration;

import com.example.foodcourt.infrastructure.security.JwtSecurityContext;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignClientConfig {
    @Bean
    public RequestInterceptor feignClientInterceptor() {
        return requestTemplate -> {
            var authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.getDetails() instanceof JwtSecurityContext context) {
                String token = context.getJwt();
                requestTemplate.header("Authorization", "Bearer " + token);
            }
        };
    }
}
