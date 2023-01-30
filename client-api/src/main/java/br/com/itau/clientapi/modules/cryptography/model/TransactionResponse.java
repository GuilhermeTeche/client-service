package br.com.itau.clientapi.modules.cryptography.model;

import br.com.itau.clientapi.modules.jackson.CustomStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionResponse {
    private String dateTime;
    private String returnCode;
    private String nsu;
    private String amount;
    private String maskedCard;
    @JsonDeserialize(using = CustomStringDeserializer.class)
    private String authorizationCode;
    private String requestId;
    private String messages;
    private String url;
    private String status;
}
