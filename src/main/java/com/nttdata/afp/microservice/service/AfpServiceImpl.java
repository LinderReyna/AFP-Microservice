package com.nttdata.afp.microservice.service;

import com.nttdata.afp.microservice.mapper.AfpMapper;
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
    private AfpMapper afpMapper;
    @Autowired
    AfpRepository afpRepository;

    @Override
    public Afp save(Afp afp) {
        return afpMapper.toModel(afpRepository.save(afpMapper.toEntity(afp)));
    }

    @Override
    public Afp findById(Integer id) {
        return afpMapper.toModel(afpRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<Afp> findAll() {
        return afpMapper.toModel(afpRepository.findAll());
    }

    @Override
    public Afp update(Afp afp, Integer id) {
        com.nttdata.afp.microservice.entity.Afp data = afpMapper.toEntity(findById(id));
        BeanUtils.copyProperties(afp, data, afpMapper.getNullPropertyNames(afp));
        return afpMapper.toModel(afpRepository.save(data));
    }

    @Override
    public void deleteById(Integer id) {
        afpRepository.deleteById(id);
    }

    @Override
    public List<Afp> findByNameLike(String name) {
        return afpMapper.toModel(afpRepository.findByNameContainsIgnoreCase(name)
                .orElseThrow(ResourceNotFoundException::new));
    }
}
