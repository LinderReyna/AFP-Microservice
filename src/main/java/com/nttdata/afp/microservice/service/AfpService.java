package com.nttdata.afp.microservice.service;

import com.nttdata.afp.microservice.model.Afp;

import java.util.List;

public interface AfpService {
    Afp save(Afp afp);
    Afp findById(Integer id);
    List<Afp> findAll();
    Afp update(Afp afp, Integer id);
    void deleteById(Integer id);
    List<Afp> findByNameLike(String name);
}
