package com.nttdata.afp.microservice.repository;

import com.nttdata.afp.microservice.entity.Afp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AfpRepository extends JpaRepository<Afp, Integer> {
    Optional<List<Afp>> findByNameContainsIgnoreCase(String name);
}
