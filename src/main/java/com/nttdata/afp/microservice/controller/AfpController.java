package com.nttdata.afp.microservice.controller;

import com.nttdata.afp.microservice.api.AfpApi;
import com.nttdata.afp.microservice.mapper.AfpMapper;
import com.nttdata.afp.microservice.model.Afp;
import com.nttdata.afp.microservice.service.AfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AfpController implements AfpApi {

    private static final String TIMESTAMP = "timestamp";
    @Autowired
    private AfpService afpService;
    @Autowired
    private AfpMapper afpMapper;

    @Override
    public ResponseEntity<Map<String, Object>> addAfp(Afp afp) {
        Map<String, Object> response = new HashMap<>();
        response.put("customer", afpMapper.toModel(afpService.save(afpMapper.toEntity(afp))));
        response.put("message", "AFP guardado con éxito");
        response.put(TIMESTAMP, new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<Afp>> allAfp() {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                afpService.findAll().stream().map(afpMapper::toModel).collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<Void> deleteAfp(Integer id) {
        return afpService.findById(id)
                .map(x -> {
                    afpService.delete(x);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Afp> getAfpById(Integer id) {
        return afpService.findById(id)
                .map(afpMapper::toModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Afp>> getByNameLike(String name) {
        return afpService.findByNameLike(name)
                .map(x -> x.stream().map(afpMapper::toModel).collect(Collectors.toList()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateAfp(Integer id, Afp afp) {
        Map<String, Object> response = new HashMap<>();
        return afpService.findById(id)
                .map(x -> {
                    response.put("customer", afpService.update(afpMapper.toEntity(afp)));
                    response.put("message", "AFP guardado con éxito");
                    response.put(TIMESTAMP, new Date());
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
