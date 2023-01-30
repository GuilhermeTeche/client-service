package br.com.itau.clientapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/capture/**").allowedOrigins("*").allowedMethods("OPTIONS", "GET", "POST");
                registry.addMapping("/refund").allowedOrigins("*").allowedMethods("OPTIONS", "GET", "PUT");
            }
        };
    }
}
