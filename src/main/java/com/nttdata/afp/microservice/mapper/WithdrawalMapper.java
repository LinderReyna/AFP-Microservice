package com.nttdata.afp.microservice.mapper;

import com.nttdata.afp.microservice.model.Withdrawal;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class WithdrawalMapper implements EntityMapper<Withdrawal, com.nttdata.afp.microservice.entity.Withdrawal> {
    @Override
    public com.nttdata.afp.microservice.entity.Withdrawal toEntity(Withdrawal dto) {
        com.nttdata.afp.microservice.entity.Withdrawal withdrawal = new com.nttdata.afp.microservice.entity.Withdrawal();
        BeanUtils.copyProperties(dto,withdrawal);
        return withdrawal;
    }

    @Override
    public Withdrawal toModel(com.nttdata.afp.microservice.entity.Withdrawal entity) {
        Withdrawal withdrawal = new Withdrawal();
        BeanUtils.copyProperties(entity,withdrawal);
        return withdrawal;
    }
}
