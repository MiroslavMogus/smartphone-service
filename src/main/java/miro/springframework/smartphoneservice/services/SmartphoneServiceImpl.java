package miro.springframework.smartphoneservice.services;

import miro.springframework.smartphoneservice.domain.Smartphone;
import miro.springframework.smartphoneservice.repositories.SmartphoneRepository;
import miro.springframework.smartphoneservice.web.controller.NotFoundException;
import miro.springframework.smartphoneservice.web.mappers.SmartphoneMapper;
import miro.springframework.smartphoneservice.web.model.SmartphoneDto;
import miro.springframework.smartphoneservice.web.model.SmartphonePagedList;
import miro.springframework.smartphoneservice.web.model.SmartphoneManufacturerEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class SmartphoneServiceImpl implements SmartphoneService {
    private final SmartphoneRepository smartphoneRepository;
    private final SmartphoneMapper smartphoneMapper;

    @Cacheable(cacheNames = "smartphoneListCache", condition = "#showInventoryOnHand == false ")
    @Override
    public SmartphonePagedList listSmartphones(String smartphoneName, SmartphoneManufacturerEnum smartphoneManufacturer, PageRequest pageRequest, Boolean showInventoryOnHand) {

        SmartphonePagedList smartphonePagedList;
        Page<Smartphone> smartphonePage;

        if (!StringUtils.isEmpty(smartphoneName) && !StringUtils.isEmpty(smartphoneManufacturer)) {
            //search both
            smartphonePage = smartphoneRepository.findAllBySmartphoneNameAndSmartphoneManufacturer(smartphoneName, smartphoneManufacturer, pageRequest);
        } else if (!StringUtils.isEmpty(smartphoneName) && StringUtils.isEmpty(smartphoneManufacturer)) {
            //search smartphone_service name
            smartphonePage = smartphoneRepository.findAllBySmartphoneName(smartphoneName, pageRequest);
        } else if (StringUtils.isEmpty(smartphoneName) && !StringUtils.isEmpty(smartphoneManufacturer)) {
            //search smartphone_service style
            smartphonePage = smartphoneRepository.findAllBySmartphoneManufacturer(smartphoneManufacturer, pageRequest);
        } else {
            smartphonePage = smartphoneRepository.findAll(pageRequest);
        }

        if (showInventoryOnHand){
            smartphonePagedList = new SmartphonePagedList(smartphonePage
                    .getContent()
                    .stream()
                    .map(smartphoneMapper::smartphoneToSmartphoneDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(smartphonePage.getPageable().getPageNumber(),
                                    smartphonePage.getPageable().getPageSize()),
                    smartphonePage.getTotalElements());
        } else {
            smartphonePagedList = new SmartphonePagedList(smartphonePage
                    .getContent()
                    .stream()
                    .map(smartphoneMapper::smartphoneToSmartphoneDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(smartphonePage.getPageable().getPageNumber(),
                                    smartphonePage.getPageable().getPageSize()),
                    smartphonePage.getTotalElements());
        }

        return smartphonePagedList;
    }

    @Cacheable(cacheNames = "smartphoneCache", key = "#smartphoneId", condition = "#showInventoryOnHand == false ")
    @Override
    public SmartphoneDto getById(UUID smartphoneId, Boolean showInventoryOnHand) {
        if (showInventoryOnHand) {
            return smartphoneMapper.smartphoneToSmartphoneDtoWithInventory(
                    smartphoneRepository.findById(smartphoneId).orElseThrow(NotFoundException::new)
            );
        } else {
            return smartphoneMapper.smartphoneToSmartphoneDto(
                    smartphoneRepository.findById(smartphoneId).orElseThrow(NotFoundException::new)
            );
        }
    }

    @Override
    public SmartphoneDto saveNewSmartphone(SmartphoneDto smartphoneDto) {
        return smartphoneMapper.smartphoneToSmartphoneDto(smartphoneRepository.save(smartphoneMapper.smartphoneDtoToSmartphone(smartphoneDto)));
    }

    @Override
    public SmartphoneDto updateSmartphone(UUID smartphoneId, SmartphoneDto smartphoneDto) {
        Smartphone smartphone = smartphoneRepository.findById(smartphoneId).orElseThrow(NotFoundException::new);

        smartphone.setSmartphoneName(smartphoneDto.getSmartphoneName());
        smartphone.setSmartphoneManufacturer(smartphoneDto.getSmartphoneManufacturer().name());
        smartphone.setPrice(smartphoneDto.getPrice());
        smartphone.setUpc(smartphoneDto.getUpc());

        return smartphoneMapper.smartphoneToSmartphoneDto(smartphoneRepository.save(smartphone));
    }

    @Cacheable(cacheNames = "smartphoneUpcCache")
    @Override
    public SmartphoneDto getByUpc(String upc) {
        return smartphoneMapper.smartphoneToSmartphoneDto(smartphoneRepository.findByUpc(upc));
    }
}
