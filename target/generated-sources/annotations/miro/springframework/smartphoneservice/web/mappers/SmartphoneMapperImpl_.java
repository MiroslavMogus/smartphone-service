package miro.springframework.smartphoneservice.web.mappers;

import javax.annotation.processing.Generated;
import miro.springframework.smartphoneservice.domain.Smartphone;
import miro.springframework.smartphoneservice.domain.Smartphone.SmartphoneBuilder;
import miro.springframework.smartphoneservice.web.model.SmartphoneDto;
import miro.springframework.smartphoneservice.web.model.SmartphoneDto.SmartphoneDtoBuilder;
import miro.springframework.smartphoneservice.web.model.SmartphoneManufacturerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-10-11T15:40:09+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.2 (JetBrains s.r.o)"
)
@Component
@Qualifier("delegate")
public class SmartphoneMapperImpl_ implements SmartphoneMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public SmartphoneDto smartphoneToSmartphoneDto(Smartphone smartphone) {
        if ( smartphone == null ) {
            return null;
        }

        SmartphoneDtoBuilder smartphoneDto = SmartphoneDto.builder();

        smartphoneDto.id( smartphone.getId() );
        if ( smartphone.getVersion() != null ) {
            smartphoneDto.version( smartphone.getVersion().intValue() );
        }
        smartphoneDto.createdDate( dateMapper.asOffsetDateTime( smartphone.getCreatedDate() ) );
        smartphoneDto.lastModifiedDate( dateMapper.asOffsetDateTime( smartphone.getLastModifiedDate() ) );
        smartphoneDto.smartphoneName( smartphone.getSmartphoneName() );
        if ( smartphone.getSmartphoneManufacturer() != null ) {
            smartphoneDto.smartphoneManufacturer( Enum.valueOf( SmartphoneManufacturerEnum.class, smartphone.getSmartphoneManufacturer() ) );
        }
        smartphoneDto.upc( smartphone.getUpc() );
        smartphoneDto.price( smartphone.getPrice() );

        return smartphoneDto.build();
    }

    @Override
    public SmartphoneDto smartphoneToSmartphoneDtoWithInventory(Smartphone smartphone) {
        if ( smartphone == null ) {
            return null;
        }

        SmartphoneDtoBuilder smartphoneDto = SmartphoneDto.builder();

        smartphoneDto.id( smartphone.getId() );
        if ( smartphone.getVersion() != null ) {
            smartphoneDto.version( smartphone.getVersion().intValue() );
        }
        smartphoneDto.createdDate( dateMapper.asOffsetDateTime( smartphone.getCreatedDate() ) );
        smartphoneDto.lastModifiedDate( dateMapper.asOffsetDateTime( smartphone.getLastModifiedDate() ) );
        smartphoneDto.smartphoneName( smartphone.getSmartphoneName() );
        if ( smartphone.getSmartphoneManufacturer() != null ) {
            smartphoneDto.smartphoneManufacturer( Enum.valueOf( SmartphoneManufacturerEnum.class, smartphone.getSmartphoneManufacturer() ) );
        }
        smartphoneDto.upc( smartphone.getUpc() );
        smartphoneDto.price( smartphone.getPrice() );

        return smartphoneDto.build();
    }

    @Override
    public Smartphone smartphoneDtoToSmartphone(SmartphoneDto dto) {
        if ( dto == null ) {
            return null;
        }

        SmartphoneBuilder smartphone = Smartphone.builder();

        smartphone.id( dto.getId() );
        if ( dto.getVersion() != null ) {
            smartphone.version( dto.getVersion().longValue() );
        }
        smartphone.createdDate( dateMapper.asTimestamp( dto.getCreatedDate() ) );
        smartphone.lastModifiedDate( dateMapper.asTimestamp( dto.getLastModifiedDate() ) );
        smartphone.smartphoneName( dto.getSmartphoneName() );
        if ( dto.getSmartphoneManufacturer() != null ) {
            smartphone.smartphoneManufacturer( dto.getSmartphoneManufacturer().name() );
        }
        smartphone.upc( dto.getUpc() );
        smartphone.price( dto.getPrice() );

        return smartphone.build();
    }
}
