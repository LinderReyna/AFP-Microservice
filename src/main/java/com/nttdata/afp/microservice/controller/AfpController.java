package com.nttdata.afp.microservice.controller;

import com.nttdata.afp.microservice.api.AfpApi;
import com.nttdata.afp.microservice.model.Afp;
import com.nttdata.afp.microservice.service.AfpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class AfpController implements AfpApi {

    @Autowired
    private AfpService afpService;
    @Override
    public ResponseEntity<Afp> addAfp(Afp afp) {
        Afp result = afpService.save(afp);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    public ResponseEntity<List<Afp>> allAfp() {
        List<Afp> result = afpService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<Void> deleteAfp(Integer id) {
        Optional.ofNullable(afpService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        afpService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Afp> getAfpById(Integer id) {
        Afp result = afpService.findById(id);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Afp>> getByNameLike(String name) {
        List<Afp> result = afpService.findByNameLike(name);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Afp> updateAfp(Integer id, Afp afp) {
        Afp result = afpService.update(afp, id);
        return ResponseEntity.ok(result);
    }
}
