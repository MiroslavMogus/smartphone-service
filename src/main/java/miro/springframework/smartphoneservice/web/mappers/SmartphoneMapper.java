package miro.springframework.smartphoneservice.web.mappers;

import miro.springframework.smartphoneservice.domain.Smartphone;
import miro.springframework.smartphoneservice.web.model.SmartphoneDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 *
 */
@Mapper(uses = {DateMapper.class})
@DecoratedWith(SmartphoneMapperDecorator.class)
public interface SmartphoneMapper {

    SmartphoneDto smartphoneToSmartphoneDto(Smartphone smartphone);

    SmartphoneDto smartphoneToSmartphoneDtoWithInventory(Smartphone smartphone);

    Smartphone smartphoneDtoToSmartphone(SmartphoneDto dto);
}
