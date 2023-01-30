package br.com.itau.clientapi.modules.keys.service.impl;

import br.com.itau.clientapi.config.CreditRecoveryBridgeHeaderConfiguration;
import br.com.itau.clientapi.modules.cryptography.service.impl.CreditRecoveryBridgeServiceImpl;
import br.com.itau.clientapi.modules.keys.service.CreditRecoveryBridgeKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class CreditRecoveryBridgeKeyServiceImpl extends CreditRecoveryBridgeServiceImpl implements CreditRecoveryBridgeKeyService {

    @Autowired
    private CreditRecoveryBridgeHeaderConfiguration creditRecoveryBridgeHeaderConfiguration;

    @Override
    public HttpEntity<String> createHttpHeaderCreditRecoveryBridgeKey(String access_token) {
        HttpHeaders headers = super.getHttpHeaders();
        headers.setBearerAuth(access_token);
        return new HttpEntity<>(headers);
    }
}
