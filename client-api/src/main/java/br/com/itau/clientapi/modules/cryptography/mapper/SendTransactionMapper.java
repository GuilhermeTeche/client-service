package br.com.itau.clientapi.modules.cryptography.mapper;

import br.com.itau.clientapi.modules.cryptography.model.CommercialPlace;
import br.com.itau.clientapi.modules.cryptography.model.CommercialSimplePlace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SendTransactionMapper {

    @Mapping(target = "uuid", expression = "java( comercialPlaceUuidPk.getUuid() )")
    @Mapping(target = "publicKey", expression = "java( comercialPlaceUuidPk.getPublicKey() )")
    @Mapping(target = "data", expression = "java( commercialPlaceData.getData() )")
    CommercialPlace commercialPlaceToCommercialPlace(CommercialSimplePlace commercialPlaceData, CommercialPlace comercialPlaceUuidPk);
}
