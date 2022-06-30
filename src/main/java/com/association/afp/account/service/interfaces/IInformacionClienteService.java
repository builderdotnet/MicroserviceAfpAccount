package com.association.afp.account.service.interfaces;

import com.association.afp.account.domain.Informacioncliente;
import com.association.afp.account.model.InformacionClienteModel;
import com.association.afp.account.model.ResultModel;

import java.math.BigDecimal;

public interface IInformacionClienteService {
    ResultModel<String> updateMontoDisponible(Informacioncliente entity, BigDecimal montoRetiro) throws Exception;

    ResultModel<InformacionClienteModel> getInformacionClienteByDni(Integer dni);
}
