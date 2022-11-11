package com.nttdata.afp.microservice.service;

import com.nttdata.afp.microservice.exception.InvalidDataException;
import com.nttdata.afp.microservice.mapper.AfpMapper;
import com.nttdata.afp.microservice.mapper.WithdrawalMapper;
import com.nttdata.afp.microservice.model.Withdrawal;
import com.nttdata.afp.microservice.repository.WithdrawalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class WithdrawalServiceImpl implements WithdrawalService{

    @Autowired
    private WithdrawalMapper withdrawalMapper;
    @Autowired
    private WithdrawalRepository withdrawalRepository;
    @Autowired
    private AfpService afpService;
    @Autowired
    private AfpMapper afpMapper;

    /**
     * Desactivar los registros con estado activo antes de guardar
     */
    @Override
    public Withdrawal save(Withdrawal withdrawal) {
        if (Objects.equals(withdrawal.getStatus(), Withdrawal.StatusEnum.ACTIVE)) {
            withdrawalRepository.updateStatus(Withdrawal.StatusEnum.INACTIVE.getValue(), Withdrawal.StatusEnum.ACTIVE.getValue());
        }
        return Optional.of(withdrawal).map(withdrawalMapper::toEntity)
                .map(x -> {
                    try {
                        return withdrawalRepository.save(x);
                    } catch (Exception e) {
                        throw new InvalidDataException(e.getMessage());
                    }
                })
                .map(withdrawalMapper::toModel)
                .orElseThrow(() -> new IllegalArgumentException("Check the data"));
    }

    @Override
    public Withdrawal findById(Long id) {
        return withdrawalMapper.toModel(withdrawalRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public void deleteById(Long id) {
        withdrawalRepository.deleteById(id);
    }

    /**
     * Buscar la AFP con estado activo
     */
    @Override
    public Withdrawal findByAfp(Integer id) {
        return withdrawalMapper.toModel(withdrawalRepository.findByAfpAndStatus(
                afpMapper.toEntity(afpService.findById(id)),Withdrawal.StatusEnum.ACTIVE.getValue()
        ).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<Withdrawal> getByAfp(Integer id) {
        return withdrawalMapper.toModel(withdrawalRepository.findAllByAfp(afpMapper.toEntity(afpService.findById(id)))
                .orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public Withdrawal update(Withdrawal withdrawal, Long id) {
        com.nttdata.afp.microservice.entity.Withdrawal data = withdrawalMapper.toEntity(findById(id));
        BeanUtils.copyProperties(withdrawal, data, withdrawalMapper.getNullPropertyNames(withdrawal));
        data.setStatus(withdrawal.getStatus().getValue());
        return save(withdrawalMapper.toModel(data));
    }
}
