package br.com.itau.clientapi.modules.cryptography.service.impl;

import br.com.itau.clientapi.config.CreditRecoveryBridgeTransactionConfiguration;
import br.com.itau.clientapi.modules.cryptography.model.CommercialPlace;
import br.com.itau.clientapi.modules.cryptography.model.EncryptCommercial;
import br.com.itau.clientapi.modules.cryptography.repository.CreditRecoveryBridgeTransactionRepository;
import br.com.itau.clientapi.modules.cryptography.service.CreditRecoveryBridgeTransactionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
public class CreditRecoveryBridgeTransactionServiceImpl extends CreditRecoveryBridgeServiceImpl implements CreditRecoveryBridgeTransactionService {

    @Autowired
    private RestTemplate sslRestTemplate;

    @Autowired
    private CreditRecoveryBridgeTransactionConfiguration creditRecoveryBridgeTransactionConfiguration;

    @Autowired
    private CreditRecoveryBridgeTransactionRepository creditRecoveryBridgeTransactionRepository;

    @NotNull
    @Override
    public ResponseEntity<String> sendTransaction(EncryptCommercial encryptCommercial) {
        sslRestTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return sslRestTemplate.exchange(
                creditRecoveryBridgeTransactionConfiguration.getHostname(),
                HttpMethod.POST,
                this.createHttpHeaderCreditRecoveryBridgeTransaction(encryptCommercial),
                String.class
        );
    }

    @Override
    public void deleteAccessKeyByRequestId(String requestId) {
        creditRecoveryBridgeTransactionRepository.delete(super.findAccessTokenByRequestId(requestId));
    }

    private HttpEntity<EncryptCommercial> createHttpHeaderCreditRecoveryBridgeTransaction(EncryptCommercial encryptCommercial) {
        HttpHeaders headers = super.getHttpHeaders();
        headers.setBearerAuth(super.findAccessTokenByRequestId(encryptCommercial.getUuid()).getAccessToken());
        return new HttpEntity<>(encryptCommercial, headers);
    }
}
