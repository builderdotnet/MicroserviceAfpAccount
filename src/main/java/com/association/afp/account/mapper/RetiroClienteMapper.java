package com.association.afp.account.mapper;

import com.association.afp.account.domain.Retirocliente;
import com.association.afp.account.model.InformacionClienteModel;
import com.association.afp.account.model.RetiroClienteModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")
public interface RetiroClienteMapper {
    Retirocliente ModelToEntity (RetiroClienteModel model);
    RetiroClienteModel EntityToModel (Retirocliente Retirocliente);
    List<RetiroClienteModel> EntitiesToModels(List<Retirocliente> Retiroclientes);
    void update(@MappingTarget Retirocliente entity, RetiroClienteModel model);
}
