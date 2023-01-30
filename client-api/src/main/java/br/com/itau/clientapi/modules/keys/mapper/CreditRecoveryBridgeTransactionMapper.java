package br.com.itau.clientapi.modules.keys.mapper;

import br.com.itau.clientapi.modules.cryptography.model.CreditRecoveryBridgeTransaction;
import br.com.itau.clientapi.modules.cryptography.model.CreditRecoveryBridgeTransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditRecoveryBridgeTransactionMapper {

    @Mapping(target = "requestId", expression = "java( requestId )")
    @Mapping(target = "accessToken", expression = "java( creditRecoveryBridgeTransactionResponse.getAccess_token() )")
    CreditRecoveryBridgeTransaction creditRecoveryBridgeTransactionResponseToCreditRecoveryBridgeTransaction(CreditRecoveryBridgeTransactionResponse creditRecoveryBridgeTransactionResponse, String requestId);
}
