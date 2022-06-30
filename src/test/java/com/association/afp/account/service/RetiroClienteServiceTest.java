package com.association.afp.account.service;

import com.association.afp.account.domain.Afp;
import com.association.afp.account.domain.Cliente;
import com.association.afp.account.domain.Informacioncliente;
import com.association.afp.account.domain.Retirocliente;
import com.association.afp.account.mapper.RetiroClienteMapper;
import com.association.afp.account.mapper.RetiroClienteMapperImpl;
import com.association.afp.account.model.InformacionClienteModel;
import com.association.afp.account.model.ResultModel;
import com.association.afp.account.model.RetiroClienteModel;
import com.association.afp.account.repository.InformacionclienteRepository;
import com.association.afp.account.repository.RetiroclienteRepository;
import com.association.afp.account.service.interfaces.IInformacionClienteService;
import com.association.afp.account.service.interfaces.IRetiroClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RetiroClienteServiceTest {
    @Mock
    InformacionclienteRepository informacionclienteRepository;
    @Mock
    RetiroclienteRepository  retiroclienteRepository;

    RetiroClienteMapper retiroClienteMapper;
    RetiroClienteService retiroClienteService;


    Retirocliente getRetiroSave (){
        return  new Retirocliente(1,new Cliente(),new Afp(),new BigDecimal(1000),Instant.parse ("2022-06-30T13:16:10.916738400Z"));
    }
    Informacioncliente getInformacionSave() {
        return new Informacioncliente(1, new Cliente(1, "Juan", "Calla", 44840472, "3434343", "xxxx@xxx.com", new Afp()), new Afp(), new BigDecimal(12000), Instant.parse("2022-06-30T13:16:10.916738400Z"), "123456");
    }
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        retiroClienteMapper = new RetiroClienteMapperImpl();
        retiroClienteService = new RetiroClienteService(retiroclienteRepository,retiroClienteMapper, informacionclienteRepository );
        when (retiroclienteRepository.save(any(Retirocliente.class))).thenReturn(getRetiroSave());
        when (informacionclienteRepository.save(any(Informacioncliente.class))).thenReturn(getInformacionSave());
        when (informacionclienteRepository.findByidcliente_Dni(44840472)).thenReturn(Optional.ofNullable(getInformacionSave()));


    }

    @Test
    void withDrawal() throws Exception {
        var response = new ResultModel<Integer>("Retiro de Afp realizado Correctamente!", 1, true);
        var responseNotFound = new ResultModel<Integer>("No existe informacion disponible", null, false);
        Assertions.assertEquals(response, retiroClienteService.withDrawal(new RetiroClienteModel(44840472, "",
                new BigDecimal(12000), Instant.now())));
        Assertions.assertEquals(responseNotFound, retiroClienteService.withDrawal(new RetiroClienteModel(9999999, "",
                new BigDecimal(9999999), Instant.now()) ));

    }
    @Test
    void withDrawalNotFound() throws Exception {
          var responseNotFound = new ResultModel<Integer>("No existe informacion disponible", null, false);
         Assertions.assertEquals(responseNotFound, retiroClienteService.withDrawal(new RetiroClienteModel(9999999, "",
                new BigDecimal(9999999), Instant.now()) ));

    }
    @Test
    void withDrawalMontoMinimo() throws Exception {
        var responseNotFound = new ResultModel<Integer>("Monto mínimo no cubierto por favor revise el monto mínimo a retirar", null, false);
        Assertions.assertEquals(responseNotFound, retiroClienteService.withDrawal(new RetiroClienteModel(44840472, "",
                new BigDecimal(10), Instant.now()) ));

    }

    @Test
    void withDrawalMontoMaximo() throws Exception {
        var responseNotFound =  new ResultModel<Integer>("No se puede registrar la solicitud. Monto mayor que el permitido", null, false);
        Assertions.assertEquals(responseNotFound, retiroClienteService.withDrawal(new RetiroClienteModel(44840472, "",
                new BigDecimal(199990), Instant.now()) ));

    }
}