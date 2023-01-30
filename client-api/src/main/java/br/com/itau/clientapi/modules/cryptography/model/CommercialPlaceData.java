package br.com.itau.clientapi.modules.cryptography.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommercialPlaceData {

    @JsonProperty
    private String requestId;

    @JsonProperty
    private BigDecimal amount;

    @JsonProperty
    private String cardNumber;

    @JsonProperty
    private String cardholderName;

    @JsonProperty
    private String establishmentCode;

    @JsonProperty
    private Integer expirationMonth;

    @JsonProperty
    private Integer expirationYear;

    @JsonProperty
    private String securityCode;
}
