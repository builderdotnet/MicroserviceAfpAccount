package com.association.afp.account.service;

import com.association.afp.account.domain.Cliente;
import com.association.afp.account.mapper.ClienteMapper;
import com.association.afp.account.model.ClienteModel;
import com.association.afp.account.model.ResultModel;
import com.association.afp.account.repository.ClienteRepository;
import com.association.afp.account.service.interfaces.IClienteService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ClienteService extends BaseService implements IClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ResultModel<ClienteModel> findByDni(String dni) {
        Optional<Cliente> response = clienteRepository.findByDni(dni);
        if (response.isPresent()) {
            ClienteModel clienteModel = clienteMapper.EntityToModel(response.get());
            return Ok(clienteModel);
        } else {
            return Error("Cliente no encontrado");
        }
    }

}