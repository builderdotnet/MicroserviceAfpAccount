package com.association.afp.account.service.interfaces;

import com.association.afp.account.domain.Informacioncliente;
import com.association.afp.account.domain.Retirocliente;
import com.association.afp.account.model.ResultModel;
import com.association.afp.account.model.RetiroClienteModel;
import com.association.afp.account.service.BaseService;

import java.math.BigDecimal;

public interface IRetiroClienteService {


    ResultModel<Integer> withDrawal(RetiroClienteModel model) throws Exception;


}
