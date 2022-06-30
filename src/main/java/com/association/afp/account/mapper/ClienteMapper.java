package com.association.afp.account.mapper;

import com.association.afp.account.domain.Cliente;
import com.association.afp.account.model.ClienteModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") 
public interface ClienteMapper {
    Cliente ModelToEntity (ClienteModel model);
    ClienteModel EntityToModel (Cliente Cliente);
}
