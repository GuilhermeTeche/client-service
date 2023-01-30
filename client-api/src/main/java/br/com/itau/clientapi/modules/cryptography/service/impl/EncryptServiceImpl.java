package br.com.itau.clientapi.modules.cryptography.service.impl;

import br.com.itau.clientapi.modules.cryptography.model.CommercialPlace;
import br.com.itau.clientapi.modules.cryptography.model.CommercialPlaceData;
import br.com.itau.clientapi.modules.cryptography.model.EncryptCommercial;
import br.com.itau.clientapi.modules.cryptography.service.EncryptService;
import com.google.gson.Gson;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSAEncrypter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

@Slf4j
@Service
public class EncryptServiceImpl implements EncryptService {

    @Autowired
    private KeyPublicServiceImpl keyPublicServiceImpl;

    private EncryptionMethod methodOfEnccryption = EncryptionMethod.A128CBC_HS256;

    @Override
    public String generatesCryptography(PublicKey rsaPublicKey, CommercialPlaceData data) throws NoSuchAlgorithmException, JOSEException, IllegalArgumentException {
        JWEObject jwe = new JWEObject(new JWEHeader(JWEAlgorithm.RSA_OAEP_256, methodOfEnccryption), new Payload(String.valueOf(new Gson().toJson(data))));
        jwe.encrypt(new RSAEncrypter((RSAPublicKey) rsaPublicKey, generatePredefinedCEKContentKey()));
        String serialize = jwe.serialize();
        log.info(String.valueOf(serialize));
        return serialize;
    }

    @NotNull
    @Override
    public EncryptCommercial getEncryptCommercial(@RequestBody CommercialPlace commercialPlace) throws NoSuchAlgorithmException, JOSEException, InvalidKeySpecException, IllegalArgumentException {
        return new EncryptCommercial(
                commercialPlace.getUuid(),
                generatesCryptography(keyPublicServiceImpl.getPublicKey(commercialPlace.getPublicKey()), commercialPlace.getData())
        );
    }

    private SecretKey generatePredefinedCEKContentKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(methodOfEnccryption.cekBitLength());
        return keyGenerator.generateKey();
    }
}
