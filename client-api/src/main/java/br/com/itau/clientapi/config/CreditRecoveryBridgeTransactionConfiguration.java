package br.com.itau.clientapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("creditrecoderybridgetransaction")
@Getter
@Setter
public class CreditRecoveryBridgeTransactionConfiguration {
    private String hostname;
}
