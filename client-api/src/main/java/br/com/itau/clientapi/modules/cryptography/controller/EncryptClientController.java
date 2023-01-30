package br.com.itau.clientapi.modules.cryptography.controller;

import br.com.itau.clientapi.modules.cryptography.mapper.TransactionMapper;
import br.com.itau.clientapi.modules.cryptography.mapper.TransactionResponseMapper;
import br.com.itau.clientapi.modules.cryptography.model.CommercialPlace;
import br.com.itau.clientapi.modules.cryptography.model.EncryptCommercial;
import br.com.itau.clientapi.modules.cryptography.service.CreditRecoveryBridgeTransactionService;
import br.com.itau.clientapi.modules.cryptography.service.EncryptService;
import br.com.itau.clientapi.modules.keys.controller.KeyClientController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/capture", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EncryptClientController {

    private final ObjectMapper objectMapper;

    @Autowired
    private EncryptService encryptService;

    @Autowired
    private CreditRecoveryBridgeTransactionService creditRecoveryBridgeTransactionService;

    @Autowired
    private KeyClientController keyClientController;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionResponseMapper transactionResponseMapper;

    /**
     * @param commercialPlace
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws JOSEException
     */
    @PostMapping("/transaction")
    public ResponseEntity<HttpStatus> sendTransaction(@RequestBody CommercialPlace commercialPlace) throws InvalidKeySpecException, IllegalArgumentException, NoSuchAlgorithmException, JOSEException {
        ResponseEntity<String> sendTransaction = null;

        try {
            EncryptCommercial encryptDatas = encryptService.getEncryptCommercial(commercialPlace);
            creditRecoveryBridgeTransactionService.sendTransaction(encryptDatas);
        } catch (HttpStatusCodeException ex) {
            log.error("Operation SendTransaction failed, reason: {}, body {}", ex.getCause(), ex.getRootCause());
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
