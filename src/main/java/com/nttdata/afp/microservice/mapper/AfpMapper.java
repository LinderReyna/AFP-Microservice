package com.nttdata.afp.microservice.mapper;

import com.nttdata.afp.microservice.model.Afp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AfpMapper implements EntityMapper<Afp, com.nttdata.afp.microservice.entity.Afp> {
    @Override
    public com.nttdata.afp.microservice.entity.Afp toEntity(Afp dto) {
        com.nttdata.afp.microservice.entity.Afp afp = new com.nttdata.afp.microservice.entity.Afp();
        BeanUtils.copyProperties(dto,afp);
        return afp;
    }

    @Override
    public Afp toModel(com.nttdata.afp.microservice.entity.Afp entity) {
        Afp afp = new Afp();
        BeanUtils.copyProperties(entity,afp);
        return afp;
    }

    @Override
    public List<com.nttdata.afp.microservice.entity.Afp> toEntity(List<Afp> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<Afp> toModel(List<com.nttdata.afp.microservice.entity.Afp> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public TypeMapper getType() {
        return TypeMapper.AFP;
    }
}
