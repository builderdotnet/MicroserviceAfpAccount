package com.association.afp.account.service.interfaces;


import com.association.afp.account.model.ResultModel;

import java.util.List;

public interface ICrudService<T , I> {
    ResultModel<List<T>> findAll() throws Exception;

    ResultModel<T> findById(I id) throws Exception;

    ResultModel<I> create(T entityModel) throws Exception;

    ResultModel<String> update(I id, T entityModel) throws Exception;

    ResultModel<String> deleteById(I id) throws Exception;

}
