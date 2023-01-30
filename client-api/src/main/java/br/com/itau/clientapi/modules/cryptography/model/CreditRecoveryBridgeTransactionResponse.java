package br.com.itau.clientapi.modules.cryptography.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreditRecoveryBridgeTransactionResponse {

    @JsonProperty
    private String token_type;

    @JsonProperty
    private String access_token;

    @JsonProperty
    private Long expires_in;

    @JsonProperty
    private Long consented_on;

    @JsonProperty
    private String scope;
}
