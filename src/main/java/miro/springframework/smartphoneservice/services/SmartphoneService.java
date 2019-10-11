package miro.springframework.smartphoneservice.services;

import miro.springframework.smartphoneservice.web.model.SmartphoneDto;
import miro.springframework.smartphoneservice.web.model.SmartphonePagedList;
import miro.springframework.smartphoneservice.web.model.SmartphoneManufacturerEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 *
 */
public interface SmartphoneService {
    SmartphonePagedList listSmartphones(String smartphoneName, SmartphoneManufacturerEnum smartphoneManufacturer, PageRequest pageRequest, Boolean showInventoryOnHand);

    SmartphoneDto getById(UUID smartphoneId, Boolean showInventoryOnHand);

    SmartphoneDto saveNewSmartphone(SmartphoneDto smartphoneDto);

    SmartphoneDto updateSmartphone(UUID smartphoneId, SmartphoneDto smartphoneDto);

    SmartphoneDto getByUpc(String upc);
}
