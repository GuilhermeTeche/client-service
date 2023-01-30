package br.com.itau.clientapi.modules.cryptography.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommercialPlace {

    @JsonProperty
    private String uuid;

    @JsonProperty
    private String publicKey;

    @JsonProperty
    private CommercialPlaceData data;
}
