package com.association.afp.account.service;

import com.association.afp.account.model.ResultModel;

import java.util.List;

public class BaseService {
    public static  <T> ResultModel<T> Ok ( T data ,String message ){
        return new ResultModel<T> (message, data, true);
    }
    public static  <T> ResultModel<T> Ok ( T data  ){
        return new ResultModel<T> ("", data, true);
    }
    public static  <T>  ResultModel<T> Error ( String message){

        return new ResultModel<T> (message, null, false);
    }
}
