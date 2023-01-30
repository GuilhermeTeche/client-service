package br.com.itau.clientapi.modules.cryptography.service.impl;

import br.com.itau.clientapi.modules.cryptography.service.KeyPublicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Service
public class KeyPublicServiceImpl implements KeyPublicService {

    @Override
    public PublicKey getPublicKey(String publicKeyServer) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        log.info("5- public key of client generate successfull, returning public key...");
        return keyFactory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyServer)));
    }
}
