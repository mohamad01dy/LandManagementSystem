package com.land.LandManagement.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**") // Apply CORS to all paths
        .allowedOriginPatterns("http://localhost:5173")
        .allowedMethods(
            "GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH") // Allow specific HTTP methods
        .allowedHeaders("*") // Allow all headers
        .allowCredentials(true) // Allow credentials (cookies, authorization headers)
        .maxAge(3600); // Max age for preflight requests in seconds (1 hour)
  }
}
