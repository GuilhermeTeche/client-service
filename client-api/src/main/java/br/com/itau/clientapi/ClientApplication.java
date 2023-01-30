package br.com.itau.clientapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EntityScan(basePackages = "br.com.itau.clientapi.modules.keys.service.CreditRecoveryBridgeOauthService")
//@ComponentScan(basePackageClasses={CreditRecoveryBridgeOauthService.class})
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
