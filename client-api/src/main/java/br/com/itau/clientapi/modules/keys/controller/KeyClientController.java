package br.com.itau.clientapi.modules.keys.controller;

import br.com.itau.clientapi.modules.cryptography.controller.EncryptClientController;
import br.com.itau.clientapi.modules.cryptography.mapper.SendTransactionMapper;
import br.com.itau.clientapi.modules.cryptography.model.CommercialPlace;
import br.com.itau.clientapi.modules.cryptography.model.CommercialSimplePlace;
import br.com.itau.clientapi.modules.cryptography.model.EncryptCommercial;
import br.com.itau.clientapi.modules.keys.service.CreditRecoveryBridgeOauthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.nimbusds.jose.JOSEException;
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

import static java.util.Objects.requireNonNull;

@Log4j2
@RestController
@RequestMapping(value = "/capture", produces = MediaType.APPLICATION_JSON_VALUE)
public class KeyClientController {

    @Autowired
    private CreditRecoveryBridgeOauthService creditRecoveryBridgeOauthService;

    @Autowired
    private EncryptClientController encryptClientController;

    @Autowired
    private SendTransactionMapper sendTransactionMapper;

    /**
     * @return String
     */
    @PostMapping("/key")
    public ResponseEntity<HttpStatus> key(@RequestBody CommercialSimplePlace commercialSimplePlace) throws InvalidKeySpecException, NoSuchAlgorithmException, JOSEException, JsonProcessingException {
        ResponseEntity<String> uuidAndPublicKey = creditRecoveryBridgeOauthService.getCreditRecoveryBridgeOauth();
        EncryptCommercial encryptCommercial = new Gson().fromJson(uuidAndPublicKey.getBody(), EncryptCommercial.class);

        try {
            if (!requireNonNull(uuidAndPublicKey.getBody()).isEmpty()) {
                creditRecoveryBridgeOauthService.saveAccessToken(uuidAndPublicKey, encryptCommercial);
            }

            commercialSimplePlace.getData().setRequestId(encryptCommercial.getUuid());
            return ResponseEntity.ok(
                    requireNonNull(
                            encryptClientController.sendTransaction(
                                    sendTransactionMapper.commercialPlaceToCommercialPlace(
                                            commercialSimplePlace,
                                            new Gson().fromJson(uuidAndPublicKey.getBody(), CommercialPlace.class)
                                    )
                            ).getBody()
                    )
            );
        } catch (HttpStatusCodeException ex) {
            log.error("Operation getKey failed, reason: {}, body {}", ex.getMessage(), ex.getResponseBodyAsString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
