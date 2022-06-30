package com.association.afp.account.service;

import com.association.afp.account.domain.Afp;
import com.association.afp.account.domain.Cliente;
import com.association.afp.account.domain.Informacioncliente;
import com.association.afp.account.mapper.InformacionClienteMapper;
import com.association.afp.account.mapper.InformacionClienteMapperImpl;
import com.association.afp.account.model.AfpModel;
import com.association.afp.account.model.InformacionClienteModel;
import com.association.afp.account.model.ResultModel;
import com.association.afp.account.repository.InformacionclienteRepository;
import com.association.afp.account.service.interfaces.IAfpService;
import com.association.afp.account.service.interfaces.IInformacionClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class InformacionClienteServiceTest {

    @Mock
    InformacionclienteRepository informacionclienteRepository;
    InformacionClienteMapper informacionClienteMapper;
    InformacionClienteService informacionClienteService;

    Informacioncliente getData() {
        return new Informacioncliente(1, new Cliente(1, "Juan", "Calla", 44840472, "3434343", "xxxx@xxx.com", new Afp()), new Afp(), new BigDecimal(12000), Instant.parse("2022-06-30T13:16:10.916738400Z"), "123456");
    }

    List<Informacioncliente> getAllData() {
        List<Informacioncliente> informacionclientes = new ArrayList<>();
        informacionclientes.add(new Informacioncliente(1, new Cliente(1, "Juan", "Calla", 44840472, "3434343", "xxxx@xxx.com", new Afp()), new Afp(), new BigDecimal(12000), Instant.parse("2022-06-30T13:16:10.916738400Z"), "123456"));
        return informacionclientes;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        informacionClienteMapper = new InformacionClienteMapperImpl();
        informacionClienteService = new InformacionClienteService(informacionclienteRepository, informacionClienteMapper);

        when(informacionclienteRepository.findByidcliente_Dni(44840472)).thenReturn(Optional.ofNullable(getData()));
        when(informacionclienteRepository.findAll()).thenReturn(getAllData());

    }


    @Test
    void getInformacionClienteByDni() {
        var response = new ResultModel<InformacionClienteModel>("", new InformacionClienteModel(1, new Cliente(1, "Juan", "Calla", 44840472, "3434343", "xxxx@xxx.com", new Afp()), new Afp(), new BigDecimal(12000), Instant.parse("2022-06-30T13:16:10.916738400Z"), "123456"), true);
        var responseError = new ResultModel<InformacionClienteModel>("No existe retiro disponible para el dni: 99999999", null, false);

        Assertions.assertEquals(response, informacionClienteService.getInformacionClienteByDni(44840472));
        Assertions.assertEquals(responseError, informacionClienteService.getInformacionClienteByDni(99999999));
    }
}