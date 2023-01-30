package br.com.itau.clientapi.modules.cryptography.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CreditRecoveryOauth")
@Data
public class CreditRecoveryBridgeTransaction {

    @Id
    private String requestId;
    private String accessToken;
}
