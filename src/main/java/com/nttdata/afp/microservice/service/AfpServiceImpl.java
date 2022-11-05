package com.nttdata.afp.microservice.service;

import com.nttdata.afp.microservice.mapper.FactoryMapper;
import com.nttdata.afp.microservice.model.Afp;
import com.nttdata.afp.microservice.repository.AfpRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AfpServiceImpl implements AfpService{

    @Autowired
    private FactoryMapper factoryMapper;
    @Autowired
    AfpRepository afpRepository;

    @Override
    public Afp save(Afp afp) {
        return factoryMapper.getAfpMapper().toModel(afpRepository.save(factoryMapper.getAfpMapper().toEntity(afp)));
    }

    @Override
    public Afp findById(Integer id) {
        return factoryMapper.getAfpMapper().toModel(afpRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<Afp> findAll() {
        return factoryMapper.getAfpMapper().toModel(afpRepository.findAll());
    }

    @Override
    public Afp update(Afp afp, Integer id) {
        com.nttdata.afp.microservice.entity.Afp data = factoryMapper.getAfpMapper().toEntity(findById(id));
        BeanUtils.copyProperties(afp, data, factoryMapper.getAfpMapper().getNullPropertyNames(afp));
        return factoryMapper.getAfpMapper().toModel(afpRepository.save(data));
    }

    @Override
    public void deleteById(Integer id) {
        afpRepository.deleteById(id);
    }

    @Override
    public List<Afp> findByNameLike(String name) {
        return factoryMapper.getAfpMapper().toModel(afpRepository.findByNameContainsIgnoreCase(name)
                .orElseThrow(ResourceNotFoundException::new));
    }
}
