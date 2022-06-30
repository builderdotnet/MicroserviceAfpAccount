package com.association.afp.account.mapper;

import com.association.afp.account.model.InformacionClienteModel;
import com.association.afp.account.domain.Informacioncliente;
import com.association.afp.account.model.InformacionClienteModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")
public interface InformacionClienteMapper {
    Informacioncliente ModelToEntity (InformacionClienteModel model);
    InformacionClienteModel EntityToModel (Informacioncliente InformacionCliente);
    List<InformacionClienteModel> EntitiesToModels(List<Informacioncliente> InformacionClientes);
    void update(@MappingTarget Informacioncliente entity, InformacionClienteModel model);
}
