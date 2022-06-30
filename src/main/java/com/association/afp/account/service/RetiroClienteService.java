package com.association.afp.account.service;

import com.association.afp.account.domain.Afp;
import com.association.afp.account.domain.Cliente;
import com.association.afp.account.domain.Informacioncliente;
import com.association.afp.account.domain.Retirocliente;
import com.association.afp.account.mapper.InformacionClienteMapper;
import com.association.afp.account.mapper.RetiroClienteMapper;
import com.association.afp.account.model.ResultModel;
import com.association.afp.account.model.RetiroClienteModel;
import com.association.afp.account.repository.InformacionclienteRepository;
import com.association.afp.account.repository.RetiroclienteRepository;
import com.association.afp.account.service.interfaces.IRetiroClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RetiroClienteService extends BaseService implements IRetiroClienteService {
    private final RetiroclienteRepository retiroclienteRepository;
    private final RetiroClienteMapper retiroClienteMapper;
    private final InformacionclienteRepository informacionclienteRepository;

    @Override
    public ResultModel<Integer> withDrawal(RetiroClienteModel model) throws Exception {
        Optional<Informacioncliente> informacioncliente = informacionclienteRepository.findByidcliente_Dni(model.getDni());
        if (informacioncliente.isPresent()) {
            var account = informacioncliente.get();
            var valid = IsValidWithDrawal(account, model);
            if (!valid.getSucceeded()) {
                return valid;
            } else {
                return saveWithdrawal(model, account);
            }
        } else {
            return Error("No existe informacion disponible");
        }


    }
    private ResultModel<Integer> saveWithdrawal(RetiroClienteModel model, Informacioncliente cliente) throws Exception {
        var retiroInserted = retiroClienteMapper.ModelToEntity(model);
        retiroInserted.setIdcliente(cliente.getIdcliente());
        retiroInserted.setIdafp(cliente.getIdafp());
        Retirocliente retiroCliente = retiroclienteRepository.save(retiroInserted);
        Integer retiroClienteId = retiroCliente.getId();
        if (retiroClienteId > 0) {
            updateMontoDisponible(cliente, model.getMontoretiro());
            return BaseService.Ok(retiroClienteId, "Retiro de Afp realizado Correctamente!");
        } else
            return BaseService.Error("No se puedo retirar el monto de Afp!");
    }

    private ResultModel<String> updateMontoDisponible(Informacioncliente entity, BigDecimal montoRetiro) throws Exception {
        var montoDisponible = entity.getMontodisponible().subtract(montoRetiro);
        entity.setMontodisponible(montoDisponible);
        informacionclienteRepository.save(entity);
        return BaseService.Ok("", "monto disponible actualizado correctamente!");

    }
    private ResultModel<Integer> IsValidWithDrawal(Informacioncliente account, RetiroClienteModel retirocliente) {
        var montoDisponible = account.getMontodisponible();
        var porcentajeMinimoRetiro = new BigDecimal(0.5);
        var montoMinimoRetiro = montoDisponible.multiply(porcentajeMinimoRetiro);
        if (montoDisponible.compareTo(retirocliente.getMontoretiro()) == -1) {
            return BaseService.Error("No se puede registrar la solicitud. Monto mayor que el permitido");
        } else if (montoMinimoRetiro.compareTo(retirocliente.getMontoretiro()) == 1) {
            return BaseService.Error("Monto mínimo no cubierto por favor revise el monto mínimo a retirar");
        } else {
            return BaseService.Ok(0);
        }
    }
}
