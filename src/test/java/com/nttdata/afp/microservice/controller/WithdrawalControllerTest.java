package com.nttdata.afp.microservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.afp.microservice.model.Afp;
import com.nttdata.afp.microservice.model.Withdrawal;
import com.nttdata.afp.microservice.service.WithdrawalService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(WithdrawalController.class)
class WithdrawalControllerTest {
    private static final String ENDPOINT_URL = "/LINDERREYNAE/AFP-Microservice/1.0.0/withdrawal";
    @MockBean
    WithdrawalService  withdrawalService;
    @Autowired
    private MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void addWithdrawal() throws Exception {
        Withdrawal request = new Withdrawal();
        request.setAfp(1);
        request.setWithdrawalAmount(BigDecimal.valueOf(10000));
        request.setStatus(Withdrawal.StatusEnum.ACTIVE);
        Withdrawal response = new Withdrawal();
        response.setId(1L);
        response.setAfp(1);
        response.setWithdrawalAmount(BigDecimal.valueOf(10000));
        response.setStatus(Withdrawal.StatusEnum.ACTIVE);
        response.setCreatedAt(OffsetDateTime.now());
        Mockito.when(withdrawalService.save(ArgumentMatchers.any(Withdrawal.class))).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.afp").value(response.getAfp()))
                .andExpect(jsonPath("$.withdrawalAmount").value(response.getWithdrawalAmount()))
                .andExpect(jsonPath("$.status").value(response.getStatus().getValue()))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.id").value(response.getId()));
    }

    @Test
    void deleteWithdrawal() throws Exception {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setId(1L);
        withdrawal.setAfp(1);
        withdrawal.setWithdrawalAmount(BigDecimal.valueOf(10000));
        withdrawal.setStatus(Withdrawal.StatusEnum.ACTIVE);
        withdrawal.setCreatedAt(OffsetDateTime.now());
        Mockito.when(withdrawalService.findById(ArgumentMatchers.anyLong())).thenReturn(withdrawal);
        Mockito.doNothing().when(withdrawalService).deleteById(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void getAllWithdrawalByAfp() throws Exception {
        List<Withdrawal> withdrawals = new ArrayList<>();
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setId(1L);
        withdrawal.setAfp(1);
        withdrawal.setWithdrawalAmount(BigDecimal.valueOf(10000));
        withdrawal.setStatus(Withdrawal.StatusEnum.ACTIVE);
        withdrawal.setCreatedAt(OffsetDateTime.now());
        withdrawals.add(withdrawal);
        Mockito.when(withdrawalService.getByAfp(ArgumentMatchers.anyInt())).thenReturn(withdrawals);
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/afp/1/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].afp").value(withdrawals.get(0).getAfp()))
                .andExpect(jsonPath("$[0].withdrawalAmount").value(withdrawals.get(0).getWithdrawalAmount()))
                .andExpect(jsonPath("$[0].status").value(withdrawals.get(0).getStatus().getValue()))
                .andExpect(jsonPath("$[0].createdAt").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(withdrawals.get(0).getId()));
    }

    @Test
    void getWithdrawalByAfp() throws Exception {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setId(1L);
        withdrawal.setAfp(1);
        withdrawal.setWithdrawalAmount(BigDecimal.valueOf(10000));
        withdrawal.setStatus(Withdrawal.StatusEnum.ACTIVE);
        withdrawal.setCreatedAt(OffsetDateTime.now());
        Mockito.when(withdrawalService.findByAfp(ArgumentMatchers.anyInt())).thenReturn(withdrawal);
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/afp/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.afp").value(withdrawal.getAfp()))
                .andExpect(jsonPath("$.withdrawalAmount").value(withdrawal.getWithdrawalAmount()))
                .andExpect(jsonPath("$.status").value(withdrawal.getStatus().getValue()))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.id").value(withdrawal.getId()));
    }

    @Test
    void getWithdrawalById() throws Exception {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setId(1L);
        withdrawal.setAfp(1);
        withdrawal.setWithdrawalAmount(BigDecimal.valueOf(10000));
        withdrawal.setStatus(Withdrawal.StatusEnum.ACTIVE);
        withdrawal.setCreatedAt(OffsetDateTime.now());
        Mockito.when(withdrawalService.findById(ArgumentMatchers.anyLong())).thenReturn(withdrawal);
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.afp").value(withdrawal.getAfp()))
                .andExpect(jsonPath("$.withdrawalAmount").value(withdrawal.getWithdrawalAmount()))
                .andExpect(jsonPath("$.status").value(withdrawal.getStatus().getValue()))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.id").value(withdrawal.getId()));
    }

    @Test
    void updateWithdrawal() throws Exception {
        Withdrawal request = new Withdrawal();
        request.setAfp(1);
        request.setWithdrawalAmount(BigDecimal.valueOf(5000));
        request.setStatus(Withdrawal.StatusEnum.ACTIVE);
        Withdrawal response = new Withdrawal();
        response.setId(1L);
        response.setAfp(1);
        response.setWithdrawalAmount(BigDecimal.valueOf(5000));
        response.setStatus(Withdrawal.StatusEnum.ACTIVE);
        response.setCreatedAt(OffsetDateTime.now());
        Mockito.when(withdrawalService.update(ArgumentMatchers.any(Withdrawal.class), ArgumentMatchers.anyLong())).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.afp").value(response.getAfp()))
                .andExpect(jsonPath("$.withdrawalAmount").value(response.getWithdrawalAmount()))
                .andExpect(jsonPath("$.status").value(response.getStatus().getValue()))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.id").value(response.getId()));
    }
}