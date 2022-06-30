package com.association.afp.account.mapper;

import com.association.afp.account.domain.Afp;
import com.association.afp.account.model.AfpModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")
public interface AfpMapper {
    Afp ModelToEntity (AfpModel model);
    AfpModel EntityToModel (Afp Afp);
    List<AfpModel> EntitiesToModels(List<Afp> Afps);
    void update(@MappingTarget Afp entity, AfpModel model);
}
