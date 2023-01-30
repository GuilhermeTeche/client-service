package br.com.itau.clientapi.modules.cryptography.mapper;

import br.com.itau.clientapi.modules.cryptography.model.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionResponseMapper {

    TransactionResponse sendTransactionToTransactionResponse(TransactionResponse sendTransaction);
}
