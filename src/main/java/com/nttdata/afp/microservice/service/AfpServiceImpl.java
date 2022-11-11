package com.nttdata.afp.microservice.service;

import com.nttdata.afp.microservice.exception.InvalidDataException;
import com.nttdata.afp.microservice.mapper.AfpMapper;
import com.nttdata.afp.microservice.model.Afp;
import com.nttdata.afp.microservice.repository.AfpRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AfpServiceImpl implements AfpService{

    @Autowired
    private AfpMapper afpMapper;
    @Autowired
    AfpRepository afpRepository;

    @Override

    public Afp save(Afp afp) {
        return Optional.of(afp).map(afpMapper::toEntity)
                .map(x -> {
                    try {
                        return afpRepository.save(x);
                    } catch (DataIntegrityViolationException e){
                        throw new DuplicateKeyException("Data duplicada");
                    } catch (Exception e) {
                        throw new InvalidDataException(e.getMessage());
                    }
                })
                .map(afpMapper::toModel)
                .orElseThrow(() -> new IllegalArgumentException("Check the data"));
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
        Afp data = findById(id);
        BeanUtils.copyProperties(afp, data, afpMapper.getNullPropertyNames(afp));
        return save(data);
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
