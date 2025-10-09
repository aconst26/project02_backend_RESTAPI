package com.example.sportsbetting;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS requests from any origin to any endpoint.
        // You can tighten this by using allowedOrigins or allowedOriginPatterns and allowedMethods, etc.
        registry.addMapping("/**");
    }
}
