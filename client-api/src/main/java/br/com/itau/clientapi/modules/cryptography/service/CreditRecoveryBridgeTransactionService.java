package br.com.itau.clientapi.modules.cryptography.service;

import br.com.itau.clientapi.modules.cryptography.model.CommercialPlace;
import br.com.itau.clientapi.modules.cryptography.model.EncryptCommercial;
import org.springframework.http.ResponseEntity;

public interface CreditRecoveryBridgeTransactionService {
    ResponseEntity<String> sendTransaction(EncryptCommercial encryptCommercial);
    void deleteAccessKeyByRequestId(String requestId);
}
