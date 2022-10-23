package com.nttdata.afp.microservice.service;

import com.nttdata.afp.microservice.domain.Afp;

import java.util.List;
import java.util.Optional;

public interface AfpService {
    Afp save(Afp afp);
    Optional<Afp> findById(Integer id);
    List<Afp> findAll();
    Afp update(Afp afp);
    void delete(Afp afp);
    Optional<List<Afp>> findByNameLike(String name);
}
