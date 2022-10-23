package com.nttdata.afp.microservice.mapper;

import com.nttdata.afp.microservice.model.Afp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AfpMapper implements EntityMapper<Afp, com.nttdata.afp.microservice.domain.Afp> {
    @Override
    public com.nttdata.afp.microservice.domain.Afp toDomain(Afp dto) {
        com.nttdata.afp.microservice.domain.Afp afp = new com.nttdata.afp.microservice.domain.Afp();
        BeanUtils.copyProperties(dto,afp);
        return afp;
    }

    @Override
    public Afp toDto(com.nttdata.afp.microservice.domain.Afp entity) {
        Afp afp = new Afp();
        BeanUtils.copyProperties(entity,afp);
        return afp;
    }
}
