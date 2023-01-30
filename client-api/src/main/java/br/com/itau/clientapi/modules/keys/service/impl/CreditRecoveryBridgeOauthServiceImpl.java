package br.com.itau.clientapi.modules.keys.service.impl;

import br.com.itau.clientapi.config.CreditRecoveryBridgeHeaderConfiguration;
import br.com.itau.clientapi.config.CreditRecoveryBridgeOauthConfiguration;
import br.com.itau.clientapi.modules.cryptography.model.CreditRecoveryBridgeTransaction;
import br.com.itau.clientapi.modules.cryptography.model.CreditRecoveryBridgeTransactionResponse;
import br.com.itau.clientapi.modules.cryptography.model.EncryptCommercial;
import br.com.itau.clientapi.modules.cryptography.repository.CreditRecoveryBridgeTransactionRepository;
import br.com.itau.clientapi.modules.keys.mapper.CreditRecoveryBridgeTransactionMapper;
import br.com.itau.clientapi.modules.keys.service.CreditRecoveryBridgeOauthService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
public class CreditRecoveryBridgeOauthServiceImpl implements CreditRecoveryBridgeOauthService {

    @Autowired
    private CreditRecoveryBridgeTransactionRepository creditRecovertBridgeTransactionRepository;

    @Autowired
    private CreditRecoveryBridgeTransactionMapper creditRecoveryBridgeTransactionMapper;

    @Autowired
    private RestTemplate sslRestTemplate;

    @Autowired
    private CreditRecoveryBridgeOauthConfiguration creditRecoveryBridgeOauthConfiguration;

    @Autowired
    private CreditRecoveryBridgeHeaderConfiguration creditRecoveryBridgeHeaderConfiguration;

    @Override
    public HttpEntity<MultiValueMap<String, String>> createHttpHeaderCreditRecoveryBridgeOauth() {
        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth(ibmHeaderConfiguration.getUsernameBasicAuth(), ibmHeaderConfiguration.getPasswordBasicAuth());
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("grant_type", "client_credentials");
//        map.add("scope", "/capture");

        return new HttpEntity<>(map, headers);
    }

    @Override
    public CreditRecoveryBridgeTransaction saveAccessToken(ResponseEntity<String> access_token, EncryptCommercial key) {
        return creditRecovertBridgeTransactionRepository.save(
                transformToIbmTransaction(
                        new Gson().fromJson(access_token.getBody(), CreditRecoveryBridgeTransactionResponse.class),
                        key.getUuid()
                )
        );
    }

    @Override
    public ResponseEntity<String> getCreditRecoveryBridgeOauth() {
        sslRestTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return sslRestTemplate.exchange(
                creditRecoveryBridgeOauthConfiguration.getHostname(),
                HttpMethod.GET,
                createHttpHeaderCreditRecoveryBridgeOauth(),
                String.class
        );
    }

    private CreditRecoveryBridgeTransaction transformToIbmTransaction(CreditRecoveryBridgeTransactionResponse creditRecoveryBridgeTransactionResponse, String requestId) {
        return creditRecoveryBridgeTransactionMapper.creditRecoveryBridgeTransactionResponseToCreditRecoveryBridgeTransaction(creditRecoveryBridgeTransactionResponse, requestId);
    }
}
