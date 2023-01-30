package br.com.itau.clientapi.modules.cryptography.service;

import br.com.itau.clientapi.modules.cryptography.model.CommercialPlace;
import br.com.itau.clientapi.modules.cryptography.model.CommercialPlaceData;
import br.com.itau.clientapi.modules.cryptography.model.EncryptCommercial;
import com.nimbusds.jose.JOSEException;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

public interface EncryptService {
    String generatesCryptography(PublicKey rsaPublicKey, CommercialPlaceData data) throws NoSuchAlgorithmException, JOSEException;

    EncryptCommercial getEncryptCommercial(@RequestBody CommercialPlace commercialPlace) throws NoSuchAlgorithmException, JOSEException, InvalidKeySpecException, IllegalArgumentException;
}
