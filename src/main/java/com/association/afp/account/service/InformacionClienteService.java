package com.association.afp.account.service;

import com.association.afp.account.domain.Afp;
import com.association.afp.account.domain.Informacioncliente;
import com.association.afp.account.mapper.InformacionClienteMapper;
import com.association.afp.account.model.AfpModel;
import com.association.afp.account.model.InformacionClienteModel;
import com.association.afp.account.model.ResultModel;
import com.association.afp.account.repository.InformacionclienteRepository;
import com.association.afp.account.service.interfaces.IInformacionClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InformacionClienteService extends BaseService implements IInformacionClienteService {
    private final InformacionclienteRepository informacionclienteRepository;
    private final InformacionClienteMapper informacionClienteMapper;

    @Override
    public ResultModel<String> updateMontoDisponible(Informacioncliente entity, BigDecimal montoRetiro) throws Exception {
        var montoDisponible = entity.getMontodisponible().subtract(montoRetiro);
        entity.setMontodisponible(montoDisponible);
        informacionclienteRepository.save(entity);
        return Ok("", "monto disponible actualizado correctamente!");

    }

    @Override
    public ResultModel<InformacionClienteModel> getInformacionClienteByDni(Integer dni) {
        List<Informacioncliente> informacionclientes = informacionclienteRepository.findAll();
        Optional<InformacionClienteModel> informacioncliente = informacionclientes.stream()
                .filter(x -> x.getIdcliente().getDni().equals(dni))
                .map(x -> informacionClienteMapper.EntityToModel(x)).findFirst();
        if (informacioncliente.isPresent()) {
            var respone = informacioncliente.get();
            return Ok(respone);
        } else {
            return Error("No existe retiro disponible para el dni: " + dni);
        }
    }
}
