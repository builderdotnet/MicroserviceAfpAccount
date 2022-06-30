package com.association.afp.account.controller;

import com.association.afp.account.model.AfpModel;
import com.association.afp.account.model.InformacionClienteModel;
import com.association.afp.account.model.ResultModel;
import com.association.afp.account.model.RetiroClienteModel;
import com.association.afp.account.service.InformacionClienteService;
import com.association.afp.account.service.RetiroClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Slf4j
public class AccountController {
    private final InformacionClienteService informacionClienteService;
    private final RetiroClienteService retiroClienteService;

    @GetMapping(path = {"account/{dni}"}, produces = {"application/json"})
    public ResponseEntity<ResultModel<InformacionClienteModel> > getInformacion( @PathVariable("dni") Integer dni) throws Exception {
        var response = informacionClienteService.getInformacionClienteByDni(dni);

        log.info("getInformacion Ok");
        log.debug(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(path = "account/retiro")
    public ResponseEntity<ResultModel<Integer>> create(@RequestBody RetiroClienteModel model) throws Exception {
        ResultModel<Integer> response = retiroClienteService.withDrawal(model);
        log.info("create Ok");
        log.debug(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
