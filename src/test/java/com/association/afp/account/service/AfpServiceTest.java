package com.association.afp.account.service;

import com.association.afp.account.domain.Afp;
import com.association.afp.account.mapper.AfpMapper;
import com.association.afp.account.mapper.AfpMapperImpl;
import com.association.afp.account.model.AfpModel;
import com.association.afp.account.model.ResultModel;
import com.association.afp.account.repository.AfpRepository;
import com.association.afp.account.service.interfaces.IAfpService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AfpServiceTest {

    @Mock
    AfpRepository afpRepository;

    AfpMapper afpMapper;
    AfpService afpService;

    List<Afp> getListData() {
        List<Afp> AllAfps = new ArrayList<Afp>();
        AllAfps.add(new Afp());
        return AllAfps;
    }

    Afp getDataFirst() {
        var response = new Afp(2, "INTEGRA");
        return response;
    }


Afp getResultCreate() {

        return new Afp(7,"CLAE");
    }


    @BeforeEach
    void setUp() throws Exception {

        MockitoAnnotations.openMocks(this);
        afpMapper = new AfpMapperImpl();
        afpService = new AfpService(afpRepository, afpMapper);

        when(afpRepository.findAll()).thenReturn(getListData());
        when(afpRepository.findById(2)).thenReturn(Optional.ofNullable(getDataFirst()));

        when(afpRepository.save(any(Afp.class))).thenReturn(getResultCreate());

    }

    @Test
    void findAll() throws Exception {
        List<Afp> AllAfps = new ArrayList<Afp>();
        AllAfps.add(new Afp());
        var response = new ResultModel<List<AfpModel>>("", afpMapper.EntitiesToModels(AllAfps), true);
        Assertions.assertEquals(response, afpService.findAll());
    }

    @Test
    void findById() throws Exception {
        var response = new ResultModel<AfpModel>("", new AfpModel(2L, "INTEGRA"), true);
        var responseError = new ResultModel<AfpModel>("Afp no encontrada!", null, false);
        Assertions.assertEquals(response, afpService.findById(2));
        Assertions.assertEquals(responseError, afpService.findById(999999));

    }

    @Test
    void create() throws Exception {
        var request = new AfpModel(7L, "CLAE");
        var responseOk = new ResultModel<Integer>("Afp Registrada Correctamente!", 7, true);
         Assertions.assertEquals(responseOk, afpService.create(request));
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}