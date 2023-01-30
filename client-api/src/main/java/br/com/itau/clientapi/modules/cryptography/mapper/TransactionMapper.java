package br.com.itau.clientapi.modules.cryptography.mapper;

import br.com.itau.clientapi.modules.cryptography.model.CommercialPlace;
import br.com.itau.clientapi.modules.cryptography.model.CommercialPlaceData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "data", expression = "java( comercialPlaceData )")
    CommercialPlace exchangeToCommercialPlace(CommercialPlace encryptCommercial, CommercialPlaceData comercialPlaceData);
}
