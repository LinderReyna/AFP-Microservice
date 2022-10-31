package com.nttdata.afp.microservice.controller;

import com.nttdata.afp.microservice.api.WithdrawalApi;
import com.nttdata.afp.microservice.model.Withdrawal;
import com.nttdata.afp.microservice.service.WithdrawalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
public class WithdrawalController implements WithdrawalApi {
    private static final String TIMESTAMP = "timestamp";
    @Autowired
    private WithdrawalService withdrawalService;

    @Override
    public ResponseEntity<Map<String, Object>> addWithdrawal(Withdrawal withdrawal) {
        Map<String, Object> response = new HashMap<>();
        response.put("customer", withdrawalService.save(withdrawal));
        response.put("message", "Retiro AFP guardado con éxito");
        response.put(TIMESTAMP, new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<Void> deleteWithdrawal(Integer id) {
        Optional.ofNullable(withdrawalService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        withdrawalService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<List<Withdrawal>> getAllWithdrawalByAfp(Integer id) {
        List<Withdrawal> withdrawals = withdrawalService.getByAfp(id);
        return ResponseEntity.ok(withdrawals);
    }

    @Override
    public ResponseEntity<Withdrawal> getWithdrawalByAfp(Integer id) {
        Withdrawal withdrawal = withdrawalService.findByAfp(id);
        return ResponseEntity.ok(withdrawal);
    }

    @Override
    public ResponseEntity<Withdrawal> getWithdrawalById(Integer id) {
        Withdrawal withdrawal = withdrawalService.findById(id);
        return ResponseEntity.ok(withdrawal);
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateWithdrawal(Integer id, Withdrawal withdrawal) {
        Optional.ofNullable(withdrawalService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        Map<String, Object> response = new HashMap<>();
        response.put("customer", withdrawalService.update(withdrawal));
        response.put("message", "Retiro AFP guardado con éxito");
        response.put(TIMESTAMP, new Date());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
