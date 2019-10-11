package miro.springframework.smartphoneservice.web.mappers;

import miro.springframework.smartphoneservice.domain.Smartphone;
import miro.springframework.smartphoneservice.services.inventory.SmartphoneInventoryService;
import miro.springframework.smartphoneservice.web.model.SmartphoneDto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public abstract class SmartphoneMapperDecorator implements SmartphoneMapper {
    private SmartphoneInventoryService smartphoneInventoryService;
    private SmartphoneMapper mapper;

    @Autowired
    public void setSmartphoneInventoryService(SmartphoneInventoryService smartphoneInventoryService) {
        this.smartphoneInventoryService = smartphoneInventoryService;
    }

    @Autowired
    public void setMapper(SmartphoneMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public SmartphoneDto smartphoneToSmartphoneDto(Smartphone smartphone) {
       return mapper.smartphoneToSmartphoneDto(smartphone);
    }

    @Override
    public SmartphoneDto smartphoneToSmartphoneDtoWithInventory(Smartphone smartphone) {
        SmartphoneDto dto = mapper.smartphoneToSmartphoneDto(smartphone);
        dto.setQuantityOnHand(smartphoneInventoryService.getOnhandInventory(smartphone.getId()));
        return dto;
    }

    @Override
    public Smartphone smartphoneDtoToSmartphone(SmartphoneDto smartphoneDto) {
        return mapper.smartphoneDtoToSmartphone(smartphoneDto);
    }
}
