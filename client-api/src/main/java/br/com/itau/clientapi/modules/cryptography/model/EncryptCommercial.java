package br.com.itau.clientapi.modules.cryptography.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EncryptCommercial {
    private String uuid;
    private String token;
}
