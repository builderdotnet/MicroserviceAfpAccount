package com.association.afp.account.service.interfaces;

import com.association.afp.account.model.AfpModel;
import com.association.afp.account.model.ResultModel;

import java.util.List;

public interface IAfpService extends ICrudService<AfpModel, Integer> {
    @Override
    ResultModel<List<AfpModel>> findAll() throws Exception;

    @Override
    ResultModel<AfpModel> findById(Integer id) throws Exception;

    @Override
    ResultModel<Integer> create(AfpModel entityModel) throws Exception;

    @Override
    ResultModel<String> update(Integer id, AfpModel entityModel) throws Exception;

    @Override
    ResultModel<String> deleteById(Integer id) throws Exception;
}
