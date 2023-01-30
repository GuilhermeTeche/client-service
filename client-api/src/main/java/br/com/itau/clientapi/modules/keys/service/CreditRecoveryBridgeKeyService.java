package br.com.itau.clientapi.modules.keys.service;

import org.springframework.http.HttpEntity;

public interface CreditRecoveryBridgeKeyService {
    HttpEntity<String> createHttpHeaderCreditRecoveryBridgeKey(String access_token);
}
