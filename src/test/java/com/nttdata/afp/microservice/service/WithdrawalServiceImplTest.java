package com.nttdata.afp.microservice.service;

import com.nttdata.afp.microservice.entity.Afp;
import com.nttdata.afp.microservice.mapper.AfpMapper;
import com.nttdata.afp.microservice.mapper.WithdrawalMapper;
import com.nttdata.afp.microservice.model.Withdrawal;
import com.nttdata.afp.microservice.repository.WithdrawalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WithdrawalServiceImplTest {
    @Mock
    WithdrawalRepository withdrawalRepository;
    @InjectMocks
    WithdrawalServiceImpl withdrawalService;
    @Mock
    private AfpService afpService;
    @Mock
    private WithdrawalMapper withdrawalMapper;
    @Mock
    private AfpMapper afpMapper;
    private final WithdrawalMapper mapper = new WithdrawalMapper();
    private final AfpMapper mapper1 = new AfpMapper();
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Withdrawal request = new Withdrawal();
        request.setAfp(1);
        request.setWithdrawalAmount(BigDecimal.valueOf(10000));
        request.setStatus(Withdrawal.StatusEnum.ACTIVE);
        com.nttdata.afp.microservice.entity.Afp afp = new com.nttdata.afp.microservice.entity.Afp();
        afp.setId(1);
        afp.setName("HABITAT");
        afp.setDescription("HABITAT");
        com.nttdata.afp.microservice.entity.Withdrawal withdrawal = new com.nttdata.afp.microservice.entity.Withdrawal();
        withdrawal.setId(1L);
        withdrawal.setAfp(afp);
        withdrawal.setWithdrawalAmount(BigDecimal.valueOf(10000));
        withdrawal.setStatus(Withdrawal.StatusEnum.ACTIVE.getValue());
        withdrawal.setCreatedAt(OffsetDateTime.now());
        Withdrawal response = new Withdrawal();
        response.setId(1L);
        response.setAfp(1);
        response.setWithdrawalAmount(BigDecimal.valueOf(10000));
        response.setStatus(Withdrawal.StatusEnum.ACTIVE);
        response.setCreatedAt(withdrawal.getCreatedAt());
        Mockito.when(withdrawalRepository.save(ArgumentMatchers.any(com.nttdata.afp.microservice.entity.Withdrawal.class))).thenReturn(withdrawal);
        Mockito.when(withdrawalMapper.toEntity(ArgumentMatchers.any(Withdrawal.class))).thenReturn(withdrawal);
        Mockito.when(withdrawalMapper.toModel(ArgumentMatchers.any(com.nttdata.afp.microservice.entity.Withdrawal.class))).thenReturn(mapper.toModel(withdrawal));
        Withdrawal created = withdrawalService.save(request);
        assertEquals(created.getId(), response.getId());
        assertEquals(created.getAfp(), response.getAfp());
        assertEquals(created.getWithdrawalAmount(), response.getWithdrawalAmount());
        assertEquals(created.getStatus(), response.getStatus());
        assertEquals(created.getCreatedAt(), response.getCreatedAt());
    }

    @Test
    void findById() {
        com.nttdata.afp.microservice.entity.Afp afp = new com.nttdata.afp.microservice.entity.Afp();
        afp.setId(1);
        afp.setName("HABITAT");
        afp.setDescription("HABITAT");
        com.nttdata.afp.microservice.entity.Withdrawal withdrawal = new com.nttdata.afp.microservice.entity.Withdrawal();
        withdrawal.setId(1L);
        withdrawal.setAfp(afp);
        withdrawal.setWithdrawalAmount(BigDecimal.valueOf(10000));
        withdrawal.setStatus(Withdrawal.StatusEnum.ACTIVE.getValue());
        withdrawal.setCreatedAt(OffsetDateTime.now());
        Withdrawal response = new Withdrawal();
        response.setId(1L);
        response.setAfp(1);
        response.setWithdrawalAmount(BigDecimal.valueOf(10000));
        response.setStatus(Withdrawal.StatusEnum.ACTIVE);
        response.setCreatedAt(OffsetDateTime.now());
        Mockito.when(withdrawalRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(withdrawal));
        Mockito.when(withdrawalMapper.toModel(ArgumentMatchers.any(com.nttdata.afp.microservice.entity.Withdrawal.class))).thenReturn(mapper.toModel(withdrawal));
        Withdrawal found = withdrawalService.findById(1L);
        assertEquals(found.getId(), response.getId());
        assertEquals(found.getAfp(), response.getAfp());
        assertEquals(found.getWithdrawalAmount(), response.getWithdrawalAmount());
        assertEquals(found.getStatus(), response.getStatus());
        assertEquals(found.getCreatedAt(), response.getCreatedAt());
    }

    @Test
    void deleteById() {
        Mockito.doNothing().when(withdrawalRepository).deleteById(ArgumentMatchers.anyLong());
        withdrawalService.deleteById(1L);
    }

    @Test
    void findByAfp() {
        com.nttdata.afp.microservice.entity.Afp afp = new com.nttdata.afp.microservice.entity.Afp();
        afp.setId(1);
        afp.setName("HABITAT");
        afp.setDescription("HABITAT");
        com.nttdata.afp.microservice.entity.Withdrawal withdrawal = new com.nttdata.afp.microservice.entity.Withdrawal();
        withdrawal.setId(1L);
        withdrawal.setAfp(afp);
        withdrawal.setWithdrawalAmount(BigDecimal.valueOf(10000));
        withdrawal.setStatus(Withdrawal.StatusEnum.ACTIVE.getValue());
        withdrawal.setCreatedAt(OffsetDateTime.now());
        Withdrawal response = new Withdrawal();
        response.setId(1L);
        response.setAfp(1);
        response.setWithdrawalAmount(BigDecimal.valueOf(10000));
        response.setStatus(Withdrawal.StatusEnum.ACTIVE);
        response.setCreatedAt(withdrawal.getCreatedAt());
        Mockito.when(withdrawalRepository.findByAfpAndStatus(ArgumentMatchers.any(Afp.class), ArgumentMatchers.anyString())).thenReturn(Optional.of(withdrawal));
        Mockito.when(withdrawalMapper.toModel(ArgumentMatchers.any(com.nttdata.afp.microservice.entity.Withdrawal.class))).thenReturn(mapper.toModel(withdrawal));
        Mockito.when(afpMapper.toEntity(ArgumentMatchers.any(com.nttdata.afp.microservice.model.Afp.class))).thenReturn(afp);
        Mockito.when(afpService.findById(ArgumentMatchers.anyInt())).thenReturn(mapper1.toModel(afp));
        Withdrawal found = withdrawalService.findByAfp(1);
        assertEquals(found.getId(), response.getId());
        assertEquals(found.getAfp(), response.getAfp());
        assertEquals(found.getWithdrawalAmount(), response.getWithdrawalAmount());
        assertEquals(found.getStatus(), response.getStatus());
        assertEquals(found.getCreatedAt(), response.getCreatedAt());
    }

    @Test
    void getByAfp() {
        com.nttdata.afp.microservice.entity.Afp afp = new com.nttdata.afp.microservice.entity.Afp();
        afp.setId(1);
        afp.setName("HABITAT");
        afp.setDescription("HABITAT");
        List<com.nttdata.afp.microservice.entity.Withdrawal> list = new ArrayList<>();
        com.nttdata.afp.microservice.entity.Withdrawal withdrawal = new com.nttdata.afp.microservice.entity.Withdrawal();
        withdrawal.setId(1L);
        withdrawal.setAfp(afp);
        withdrawal.setWithdrawalAmount(BigDecimal.valueOf(10000));
        withdrawal.setStatus(Withdrawal.StatusEnum.ACTIVE.getValue());
        withdrawal.setCreatedAt(OffsetDateTime.now());
        list.add(withdrawal);
        com.nttdata.afp.microservice.entity.Withdrawal withdrawal1 = new com.nttdata.afp.microservice.entity.Withdrawal();
        withdrawal1.setId(2L);
        withdrawal1.setAfp(afp);
        withdrawal1.setWithdrawalAmount(BigDecimal.valueOf(5000));
        withdrawal1.setStatus(Withdrawal.StatusEnum.INACTIVE.getValue());
        withdrawal1.setCreatedAt(OffsetDateTime.now());
        list.add(withdrawal1);
        List<Withdrawal> withdrawals = new ArrayList<>();
        Withdrawal response = new Withdrawal();
        response.setId(1L);
        response.setAfp(1);
        response.setWithdrawalAmount(BigDecimal.valueOf(10000));
        response.setStatus(Withdrawal.StatusEnum.ACTIVE);
        response.setCreatedAt(withdrawal.getCreatedAt());
        withdrawals.add(response);
        Withdrawal response1 = new Withdrawal();
        response1.setId(2L);
        response1.setAfp(1);
        response1.setWithdrawalAmount(BigDecimal.valueOf(5000));
        response1.setStatus(Withdrawal.StatusEnum.INACTIVE);
        response1.setCreatedAt(withdrawal1.getCreatedAt());
        withdrawals.add(response1);
        Mockito.when(withdrawalRepository.findAllByAfp(ArgumentMatchers.any(Afp.class))).thenReturn(Optional.of(list));
        Mockito.when(withdrawalMapper.toModel(ArgumentMatchers.anyList())).thenReturn(mapper.toModel(list));
        Mockito.when(afpMapper.toEntity(ArgumentMatchers.any(com.nttdata.afp.microservice.model.Afp.class))).thenReturn(afp);
        Mockito.when(afpService.findById(ArgumentMatchers.anyInt())).thenReturn(mapper1.toModel(afp));
        List<Withdrawal> found = withdrawalService.getByAfp(1);
        assertEquals(found.get(0).getId(), withdrawals.get(0).getId());
        assertEquals(found.get(0).getAfp(), withdrawals.get(0).getAfp());
        assertEquals(found.get(0).getWithdrawalAmount(), withdrawals.get(0).getWithdrawalAmount());
        assertEquals(found.get(0).getStatus(), withdrawals.get(0).getStatus());
        assertEquals(found.get(0).getCreatedAt(), withdrawals.get(0).getCreatedAt());
        assertEquals(found.get(1).getId(), withdrawals.get(1).getId());
        assertEquals(found.get(1).getAfp(), withdrawals.get(1).getAfp());
        assertEquals(found.get(1).getWithdrawalAmount(), withdrawals.get(1).getWithdrawalAmount());
        assertEquals(found.get(1).getStatus(), withdrawals.get(1).getStatus());
        assertEquals(found.get(1).getCreatedAt(), withdrawals.get(1).getCreatedAt());
    }

    @Test
    void update() {
        Withdrawal request = new Withdrawal();
        request.setAfp(1);
        request.setWithdrawalAmount(BigDecimal.valueOf(10000));
        request.setStatus(Withdrawal.StatusEnum.INACTIVE);
        com.nttdata.afp.microservice.entity.Afp afp = new com.nttdata.afp.microservice.entity.Afp();
        afp.setId(1);
        afp.setName("HABITAT");
        afp.setDescription("HABITAT");
        com.nttdata.afp.microservice.entity.Withdrawal withdrawal = new com.nttdata.afp.microservice.entity.Withdrawal();
        withdrawal.setId(1L);
        withdrawal.setAfp(afp);
        withdrawal.setWithdrawalAmount(BigDecimal.valueOf(10000));
        withdrawal.setStatus(Withdrawal.StatusEnum.INACTIVE.getValue());
        withdrawal.setCreatedAt(OffsetDateTime.now());
        Withdrawal response = new Withdrawal();
        response.setId(1L);
        response.setAfp(1);
        response.setWithdrawalAmount(BigDecimal.valueOf(10000));
        response.setStatus(Withdrawal.StatusEnum.INACTIVE);
        response.setCreatedAt(withdrawal.getCreatedAt());
        Mockito.when(withdrawalRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(withdrawal));
        Mockito.when(withdrawalRepository.save(ArgumentMatchers.any(com.nttdata.afp.microservice.entity.Withdrawal.class))).thenReturn(withdrawal);
        Mockito.when(withdrawalMapper.toEntity(ArgumentMatchers.any(Withdrawal.class))).thenReturn(withdrawal);
        Mockito.when(withdrawalMapper.toModel(ArgumentMatchers.any(com.nttdata.afp.microservice.entity.Withdrawal.class))).thenReturn(mapper.toModel(withdrawal));
        Withdrawal updated = withdrawalService.update(request, 1L);
        assertEquals(updated.getId(), response.getId());
        assertEquals(updated.getAfp(), response.getAfp());
        assertEquals(updated.getWithdrawalAmount(), response.getWithdrawalAmount());
        assertEquals(updated.getStatus(), response.getStatus());
        assertEquals(updated.getCreatedAt(), response.getCreatedAt());
    }
}