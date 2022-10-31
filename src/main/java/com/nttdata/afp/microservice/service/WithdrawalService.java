package com.nttdata.afp.microservice.service;

import com.nttdata.afp.microservice.model.Withdrawal;

import java.util.List;

public interface WithdrawalService {

    Withdrawal save(Withdrawal withdrawal);

    Withdrawal findById(Integer id);

    void deleteById(Integer id);

    Withdrawal findByAfp(Integer id);

    List<Withdrawal> getByAfp(Integer id);

    Withdrawal update(Withdrawal withdrawal);
}
