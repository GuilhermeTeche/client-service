package br.com.itau.clientapi.modules.cryptography.service.impl;

import br.com.itau.clientapi.config.CreditRecoveryBridgeHeaderConfiguration;
import br.com.itau.clientapi.exceptions.TransactionNotFoundException;
import br.com.itau.clientapi.modules.cryptography.model.CreditRecoveryBridgeTransaction;
import br.com.itau.clientapi.modules.cryptography.repository.CreditRecoveryBridgeTransactionRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditRecoveryBridgeServiceImpl {

    @Autowired
    private CreditRecoveryBridgeTransactionRepository creditRecoveryBridgeTransactionRepository;

    @Autowired
    private CreditRecoveryBridgeHeaderConfiguration creditRecoveryBridgeHeaderConfiguration;

    @NotNull
    public HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CreditRecoveryBridge-Client-Id", creditRecoveryBridgeHeaderConfiguration.getClientId());
        headers.set("X-CreditRecoveryBridge-Client-Secret", creditRecoveryBridgeHeaderConfiguration.getClientSecret());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public CreditRecoveryBridgeTransaction findAccessTokenByRequestId(String requestId) {
        Optional<CreditRecoveryBridgeTransaction> accessToken = creditRecoveryBridgeTransactionRepository.findAccessTokenByRequestId(requestId);

        if (!accessToken.isPresent()) {
            throw new TransactionNotFoundException();
        }

        return accessToken.get();
    }
}
