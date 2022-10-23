package com.nttdata.afp.microservice.service;

import com.nttdata.afp.microservice.domain.Afp;
import com.nttdata.afp.microservice.repository.AfpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AfpServiceImpl implements AfpService{

    @Autowired
    AfpRepository afpRepository;

    @Override
    public Afp save(Afp afp) {
        return afpRepository.save(afp);
    }

    @Override
    public Optional<Afp> findById(Integer id) {
        return afpRepository.findById(id);
    }

    @Override
    public List<Afp> findAll() {
        return afpRepository.findAll();
    }

    @Override
    public Afp update(Afp afp) {
        return afpRepository.save(afp);
    }

    @Override
    public void delete(Afp afp) {
        afpRepository.delete(afp);
    }

    @Override
    public Optional<List<Afp>> findByNameLike(String name) {
        return afpRepository.findByNameContains(name);
    }
}
