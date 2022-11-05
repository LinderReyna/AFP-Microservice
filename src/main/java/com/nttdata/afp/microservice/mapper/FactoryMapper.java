package com.nttdata.afp.microservice.mapper;

import com.nttdata.afp.microservice.model.Afp;
import com.nttdata.afp.microservice.model.Withdrawal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryMapper {
    @Autowired
    private AfpMapper afpMapper;
    @Autowired
    private WithdrawalMapper withdrawalMapper;

    public EntityMapper<Afp, com.nttdata.afp.microservice.entity.Afp> getAfpMapper(){
        return this.afpMapper;
    }
    public EntityMapper<Withdrawal, com.nttdata.afp.microservice.entity.Withdrawal> getWithdrawallMapper(){
        return this.withdrawalMapper;
    }
}
