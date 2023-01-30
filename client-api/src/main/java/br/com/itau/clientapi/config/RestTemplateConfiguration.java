package br.com.itau.clientapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfiguration {
    private static final String SSL = "SSL";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}


