package com.nttdata.afp.microservice.repository;

import com.nttdata.afp.microservice.entity.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
}
