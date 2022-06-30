package com.association.afp.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping(path = { "/xxx" }, produces = { "application/json" })
    public ResponseEntity<String> getAll() throws Exception {
        return new ResponseEntity<String>("Hola", HttpStatus.OK);
    }
}
