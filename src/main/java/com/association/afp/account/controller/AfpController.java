package com.association.afp.account.controller;

import com.association.afp.account.model.AfpModel;
import com.association.afp.account.model.ResultModel;
import com.association.afp.account.service.AfpService;
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
public class AfpController {
    private final AfpService afpService ;
    @GetMapping(path = {"afp"}, produces = {"application/json"})
    public ResponseEntity<ResultModel<List<AfpModel>>> getAll() throws Exception {
        ResultModel<List<AfpModel>> response = afpService.findAll();
        log.info("getAll Ok");
        log.debug(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = {"afp/{id}"}, produces = {"application/json"})
    public ResponseEntity<ResultModel<AfpModel>> getById(@PathVariable("id") Integer id) throws Exception {
        ResultModel<AfpModel> response = afpService.findById(id);
        log.info("getById Ok");
        log.debug(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "afp")
    public ResponseEntity<ResultModel<Integer>> create(@RequestBody AfpModel AfpModel) throws Exception {
        ResultModel<Integer> response = afpService.create(AfpModel);
        log.info("create Ok");
        log.debug(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = {"afp/{id}"}, produces = {"application/json"})
    public ResponseEntity<ResultModel<String>> update(
            @PathVariable("id") Integer id,
            @RequestBody AfpModel AfpModel) throws Exception {

        ResultModel<String> response = afpService.update(id, AfpModel);
        log.info("update Ok");
        log.debug(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping({"afp/{id}"})
    public ResponseEntity<ResultModel<String>> deleteById(@PathVariable("id") Integer id) throws Exception {
       try {
           ResultModel<String> response =  afpService.deleteById(id);
           log.info("deleteById Ok");
           log.debug(response.toString());
           return new ResponseEntity<>(response, HttpStatus.OK);
       } catch (Exception ex) {
           log.error(ex.getMessage());
          ex.printStackTrace();
           return new ResponseEntity<>(new  ResultModel<String>("Error no controlado","",false), HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }
}
