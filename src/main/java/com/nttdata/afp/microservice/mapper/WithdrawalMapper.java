package com.nttdata.afp.microservice.mapper;

import com.nttdata.afp.microservice.model.Withdrawal;
import com.nttdata.afp.microservice.service.AfpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WithdrawalMapper implements EntityMapper<Withdrawal, com.nttdata.afp.microservice.entity.Withdrawal> {
    @Autowired
    private AfpService afpService;
    @Autowired
    private AfpMapper afpMapper;
    @Override
    public com.nttdata.afp.microservice.entity.Withdrawal toEntity(Withdrawal dto) {
        com.nttdata.afp.microservice.entity.Withdrawal withdrawal = new com.nttdata.afp.microservice.entity.Withdrawal();
        BeanUtils.copyProperties(dto,withdrawal);
        withdrawal.setStatus(dto.getStatus().getValue());
        withdrawal.setAfp(afpMapper.toEntity(afpService.findById(dto.getAfp())));
        return withdrawal;
    }

    @Override
    public Withdrawal toModel(com.nttdata.afp.microservice.entity.Withdrawal entity) {
        Withdrawal withdrawal = new Withdrawal();
        BeanUtils.copyProperties(entity,withdrawal);
        withdrawal.setStatus(Withdrawal.StatusEnum.fromValue(entity.getStatus()));
        withdrawal.setAfp(entity.getAfp().getId());
        return withdrawal;
    }

    @Override
    public List<com.nttdata.afp.microservice.entity.Withdrawal> toEntity(List<Withdrawal> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<Withdrawal> toModel(List<com.nttdata.afp.microservice.entity.Withdrawal> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public TypeMapper getType() {
        return TypeMapper.WITHDRAWAL;
    }
}
