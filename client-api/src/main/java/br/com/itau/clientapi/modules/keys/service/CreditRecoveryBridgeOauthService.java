package br.com.itau.clientapi.modules.keys.service;

import br.com.itau.clientapi.modules.cryptography.model.CreditRecoveryBridgeTransaction;
import br.com.itau.clientapi.modules.cryptography.model.EncryptCommercial;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;


public interface CreditRecoveryBridgeOauthService {
    HttpEntity<MultiValueMap<String, String>> createHttpHeaderCreditRecoveryBridgeOauth();
    CreditRecoveryBridgeTransaction saveAccessToken(ResponseEntity<String> access_token, EncryptCommercial key);
    ResponseEntity<String> getCreditRecoveryBridgeOauth();
}
