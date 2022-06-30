package com.association.afp.account.service.interfaces;

import com.association.afp.account.model.ClienteModel;
import com.association.afp.account.model.ResultModel;

public interface IClienteService {
    ResultModel<ClienteModel> findByDni(String dni);
}
