package miro.springframework.smartphoneservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import miro.springframework.smartphoneservice.bootstrap.SmartphoneLoader;
import miro.springframework.smartphoneservice.services.SmartphoneService;
import miro.springframework.smartphoneservice.web.model.SmartphoneDto;
import miro.springframework.smartphoneservice.web.model.SmartphoneManufacturerEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SmartphoneController.class)
class SmartphoneControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SmartphoneService smartphoneService;

    @Test
    void getSmartphoneById() throws Exception {

        given(smartphoneService.getById(any(), anyBoolean())).willReturn(getValidSmartphoneDto());

        mockMvc.perform(get("/api/v1/smartphone/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveNewSmartphone() throws Exception {

        SmartphoneDto smartphoneDto = getValidSmartphoneDto();
        String smartphoneDtoJson = objectMapper.writeValueAsString(smartphoneDto);

        given(smartphoneService.saveNewSmartphone(any())).willReturn(getValidSmartphoneDto());

        mockMvc.perform(post("/api/v1/smartphone/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(smartphoneDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateSmartphoneById() throws Exception {
        given(smartphoneService.updateSmartphone(any(), any())).willReturn(getValidSmartphoneDto());

        SmartphoneDto smartphoneDto = getValidSmartphoneDto();
        String smartphoneDtoJson = objectMapper.writeValueAsString(smartphoneDto);

        mockMvc.perform(put("/api/v1/smartphone/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(smartphoneDtoJson))
                .andExpect(status().isNoContent());
    }

    SmartphoneDto getValidSmartphoneDto(){
        return SmartphoneDto.builder()
                .smartphoneName("Iphone 11")
                .smartphoneManufacturer(SmartphoneManufacturerEnum.APPLE)
                .price(new BigDecimal("2.99"))
                .upc(SmartphoneLoader.SMARTPHONE_1_UPC)
                .build();
    }
}