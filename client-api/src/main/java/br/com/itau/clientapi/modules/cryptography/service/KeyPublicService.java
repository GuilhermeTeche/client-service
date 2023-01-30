package br.com.itau.clientapi.modules.cryptography.service;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

public interface KeyPublicService {
    PublicKey getPublicKey(String publicKeyServer) throws InvalidKeySpecException, NoSuchAlgorithmException;
}
