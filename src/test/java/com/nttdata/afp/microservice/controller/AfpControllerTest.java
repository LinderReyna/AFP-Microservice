package com.nttdata.afp.microservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.afp.microservice.model.Afp;
import com.nttdata.afp.microservice.service.AfpService;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AfpController.class)
class AfpControllerTest {
    private static final String ENDPOINT_URL = "/LINDERREYNAE/AFP-Microservice/1.0.0/afp";
    @MockBean
    AfpService afpService;
    @Autowired
    private MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void addAfp() throws Exception {
        Afp request = new Afp();
        request.setName("HABITAT");
        request.setDescription("HABITAT");
        Afp response = new Afp();
        response.setId(1);
        response.setName("HABITAT");
        response.setDescription("HABITAT");
        Mockito.when(afpService.save(ArgumentMatchers.any(Afp.class))).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.description").value(response.getDescription()))
                .andExpect(jsonPath("$.id").value(response.getId()));
    }

    @Test
    void allAfp() throws Exception {
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
        Mockito.when(afpService.findAll()).thenReturn(afpList);
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(afpList.get(0).getName()))
                .andExpect(jsonPath("$[0].description").value(afpList.get(0).getDescription()))
                .andExpect(jsonPath("$[0].id").value(afpList.get(0).getId()))
                .andExpect(jsonPath("$[1].name").value(afpList.get(1).getName()))
                .andExpect(jsonPath("$[1].description").value(afpList.get(1).getDescription()))
                .andExpect(jsonPath("$[1].id").value(afpList.get(1).getId()));
    }

    @Test
    void deleteAfp() throws Exception {
        Afp afp = new Afp();
        afp.setId(1);
        afp.setName("HABITAT");
        afp.setDescription("HABITAT");
        Mockito.when(afpService.findById(ArgumentMatchers.anyInt())).thenReturn(afp);
        Mockito.doNothing().when(afpService).deleteById(1);
        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAfpById() throws Exception {
        Afp afp = new Afp();
        afp.setId(1);
        afp.setName("HABITAT");
        afp.setDescription("HABITAT");
        Mockito.when(afpService.findById(ArgumentMatchers.anyInt())).thenReturn(afp);
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(afp.getName()))
                .andExpect(jsonPath("$.description").value(afp.getDescription()))
                .andExpect(jsonPath("$.id").value(afp.getId()));
    }

    @Test
    void getByNameLike() throws Exception {
        List<Afp> afpList = new ArrayList<>();
        Afp afp1 = new Afp();
        afp1.setId(1);
        afp1.setName("HABITAT");
        afp1.setDescription("HABITAT");
        afpList.add(afp1);
        Mockito.when(afpService.findByNameLike(ArgumentMatchers.anyString())).thenReturn(afpList);
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/name/h")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(afpList.get(0).getName()))
                .andExpect(jsonPath("$[0].description").value(afpList.get(0).getDescription()))
                .andExpect(jsonPath("$[0].id").value(afpList.get(0).getId()));
    }

    @Test
    void updateAfp() throws Exception {
        Afp request = new Afp();
        request.setName("HABITAT");
        request.setDescription("HABITAT SAC");
        Afp response = new Afp();
        response.setId(1);
        response.setName("HABITAT");
        response.setDescription("HABITAT SAC");
        Mockito.when(afpService.update(ArgumentMatchers.any(Afp.class), ArgumentMatchers.anyInt())).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.description").value(response.getDescription()))
                .andExpect(jsonPath("$.id").value(response.getId()));
    }
}