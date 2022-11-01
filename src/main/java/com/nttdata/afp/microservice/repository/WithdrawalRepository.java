package com.nttdata.afp.microservice.repository;

import com.nttdata.afp.microservice.entity.Afp;
import com.nttdata.afp.microservice.entity.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
    Optional<Withdrawal> findByAfpAndStatus(Afp afp, String status);
    Optional<List<Withdrawal>> findAllByAfp(Afp afp);
    @Modifying
    @Query(value = "UPDATE Withdrawal t SET t.status = :status WHERE t.status = :active")
    void updateStatus(@Param("status") String status, @Param("active") String active);
}
