package com.nttdata.afp.microservice.service;

import com.nttdata.afp.microservice.mapper.WithdrawalMapper;
import com.nttdata.afp.microservice.model.Withdrawal;
import com.nttdata.afp.microservice.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawalServiceImpl implements WithdrawalService{

    @Autowired
    private WithdrawalMapper withdrawalMapper;
    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Override
    public Withdrawal save(Withdrawal withdrawal) {
        return null;
    }

    @Override
    public Withdrawal findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Withdrawal findByAfp(Integer id) {
        return null;
    }

    @Override
    public List<Withdrawal> getByAfp(Integer id) {
        return null;
    }

    @Override
    public Withdrawal update(Withdrawal withdrawal) {
        return null;
    }
}
