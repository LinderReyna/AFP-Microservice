package com.nttdata.afp.microservice.service;

import com.nttdata.afp.microservice.mapper.AfpMapper;
import com.nttdata.afp.microservice.model.Afp;
import com.nttdata.afp.microservice.repository.AfpRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AfpServiceImplTest {
    @Mock
    AfpRepository afpRepository;
    @InjectMocks
    AfpServiceImpl afpService;
    @Mock
    private AfpMapper afpMapper;
    private final AfpMapper mapper = new AfpMapper();
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void save() {
        Afp request = new Afp();
        request.setName("HABITAT");
        request.setDescription("HABITAT");
        com.nttdata.afp.microservice.entity.Afp afp = new com.nttdata.afp.microservice.entity.Afp();
        afp.setId(1);
        afp.setName("HABITAT");
        afp.setDescription("HABITAT");
        Afp response = new Afp();
        response.setId(1);
        response.setName("HABITAT");
        response.setDescription("HABITAT");
        Mockito.when(afpRepository.save(ArgumentMatchers.any(com.nttdata.afp.microservice.entity.Afp.class))).thenReturn(afp);
        Mockito.when(afpMapper.toEntity(ArgumentMatchers.any(Afp.class))).thenReturn(mapper.toEntity(request));
        Mockito.when(afpMapper.toModel(ArgumentMatchers.any(com.nttdata.afp.microservice.entity.Afp.class))).thenReturn(mapper.toModel(afp));
        Afp created = afpService.save(request);
        assertEquals(created.getName(), response.getName());
        assertEquals(created.getDescription(), response.getDescription());
        assertEquals(created.getId(), response.getId());
    }

    @Test
    void findById() {
        com.nttdata.afp.microservice.entity.Afp afp = new com.nttdata.afp.microservice.entity.Afp();
        afp.setId(1);
        afp.setName("HABITAT");
        afp.setDescription("HABITAT");
        Afp response = new Afp();
        response.setId(1);
        response.setName("HABITAT");
        response.setDescription("HABITAT");
        Mockito.when(afpRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(afp));
        Mockito.when(afpMapper.toModel(ArgumentMatchers.any(com.nttdata.afp.microservice.entity.Afp.class))).thenReturn(mapper.toModel(afp));
        Afp found = afpService.findById(1);
        assertEquals(found.getName(), response.getName());
        assertEquals(found.getDescription(), response.getDescription());
        assertEquals(found.getId(), response.getId());
    }

    @Test
    void findAll() {
        List<Afp> afpList = new ArrayList<>();
        Afp afp1 = new Afp();
        afp1.setId(1);
        afp1.setName("HABITAT");
        afp1.setDescription("HABITAT");
        afpList.add(afp1);
        Afp afp2 = new Afp();
        afp2.setId(2);
        afp2.setName("Profuturo");
        afp2.setDescription("Profuturo");
        afpList.add(afp2);
        List<com.nttdata.afp.microservice.entity.Afp> afps = new ArrayList<>();
        com.nttdata.afp.microservice.entity.Afp afp3 = new com.nttdata.afp.microservice.entity.Afp();
        afp3.setId(1);
        afp3.setName("HABITAT");
        afp3.setDescription("HABITAT");
        afps.add(afp3);
        com.nttdata.afp.microservice.entity.Afp afp4 = new com.nttdata.afp.microservice.entity.Afp();
        afp4.setId(2);
        afp4.setName("Profuturo");
        afp4.setDescription("Profuturo");
        afps.add(afp4);
        Mockito.when(afpRepository.findAll()).thenReturn(afps);
        Mockito.when(afpMapper.toModel(ArgumentMatchers.anyList())).thenReturn(mapper.toModel(afps));
        List<Afp> found = afpService.findAll();
        assertEquals(found.get(0).getName(), afpList.get(0).getName());
        assertEquals(found.get(0).getDescription(), afpList.get(0).getDescription());
        assertEquals(found.get(0).getId(), afpList.get(0).getId());
        assertEquals(found.get(1).getName(), afpList.get(1).getName());
        assertEquals(found.get(1).getDescription(), afpList.get(1).getDescription());
        assertEquals(found.get(1).getId(), afpList.get(1).getId());
    }

    @Test
    void update() {
        Afp request = new Afp();
        request.setName("HABITAT");
        request.setDescription("HABITAT SAC");
        com.nttdata.afp.microservice.entity.Afp afp = new com.nttdata.afp.microservice.entity.Afp();
        afp.setId(1);
        afp.setName("HABITAT");
        afp.setDescription("HABITAT SAC");
        Afp response = new Afp();
        response.setId(1);
        response.setName("HABITAT");
        response.setDescription("HABITAT SAC");
        Mockito.when(afpRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(afp));
        Mockito.when(afpRepository.save(ArgumentMatchers.any(com.nttdata.afp.microservice.entity.Afp.class))).thenReturn(afp);
        Mockito.when(afpMapper.toEntity(ArgumentMatchers.any(Afp.class))).thenReturn(mapper.toEntity(request));
        Mockito.when(afpMapper.toModel(ArgumentMatchers.any(com.nttdata.afp.microservice.entity.Afp.class))).thenReturn(mapper.toModel(afp));
        Mockito.when(afpMapper.getNullPropertyNames(ArgumentMatchers.any(Afp.class))).thenReturn(mapper.getNullPropertyNames(request));
        Afp updated = afpService.update(request, 1);
        assertEquals(updated.getName(), response.getName());
        assertEquals(updated.getDescription(), response.getDescription());
        assertEquals(updated.getId(), response.getId());
    }

    @Test
    void deleteById() {
        Mockito.doNothing().when(afpRepository).deleteById(ArgumentMatchers.anyInt());
        afpService.deleteById(1);
    }

    @Test
    void findByNameLike() {
        List<Afp> afpList = new ArrayList<>();
        Afp afp1 = new Afp();
        afp1.setId(1);
        afp1.setName("HABITAT");
        afp1.setDescription("HABITAT");
        afpList.add(afp1);
        List<com.nttdata.afp.microservice.entity.Afp> afps = new ArrayList<>();
        com.nttdata.afp.microservice.entity.Afp afp3 = new com.nttdata.afp.microservice.entity.Afp();
        afp3.setId(1);
        afp3.setName("HABITAT");
        afp3.setDescription("HABITAT");
        afps.add(afp3);
        Mockito.when(afpRepository.findByNameContainsIgnoreCase(ArgumentMatchers.anyString())).thenReturn(Optional.of(afps));
        Mockito.when(afpMapper.toModel(ArgumentMatchers.anyList())).thenReturn(mapper.toModel(afps));
        List<Afp> found = afpService.findByNameLike("h");
        assertEquals(found.get(0).getName(), afpList.get(0).getName());
        assertEquals(found.get(0).getDescription(), afpList.get(0).getDescription());
        assertEquals(found.get(0).getId(), afpList.get(0).getId());
    }
}