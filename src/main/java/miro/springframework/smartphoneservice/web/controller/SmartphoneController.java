package miro.springframework.smartphoneservice.web.controller;

import miro.springframework.smartphoneservice.services.SmartphoneService;
import miro.springframework.smartphoneservice.web.model.SmartphoneDto;
import miro.springframework.smartphoneservice.web.model.SmartphonePagedList;
import miro.springframework.smartphoneservice.web.model.SmartphoneManufacturerEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 *
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class SmartphoneController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final SmartphoneService smartphoneService;

    @GetMapping(produces = { "application/json" }, path = "smartphone")
    public ResponseEntity<SmartphonePagedList> listSmartphones(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                         @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                         @RequestParam(value = "smartphoneName", required = false) String smartphoneName,
                                                         @RequestParam(value = "smartphoneManufacturer", required = false) SmartphoneManufacturerEnum smartphoneManufacturer,
                                                         @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        SmartphonePagedList smartphoneList = smartphoneService.listSmartphones(smartphoneName, smartphoneManufacturer, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(smartphoneList, HttpStatus.OK);
    }

    @GetMapping("smartphone/{smartphoneId}")
    public ResponseEntity<SmartphoneDto> getSmartphoneById(@PathVariable("smartphoneId") UUID smartphoneId,
                                                     @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){
        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        return new ResponseEntity<>(smartphoneService.getById(smartphoneId, showInventoryOnHand), HttpStatus.OK);
    }

    @GetMapping("smartphoneUpc/{upc}")
    public ResponseEntity<SmartphoneDto> getSmartphoneByUpc(@PathVariable("upc") String upc){
        return new ResponseEntity<>(smartphoneService.getByUpc(upc), HttpStatus.OK);
    }

    @PostMapping(path = "smartphone")
    public ResponseEntity saveNewSmartphone(@RequestBody @Validated SmartphoneDto smartphoneDto){
        return new ResponseEntity<>(smartphoneService.saveNewSmartphone(smartphoneDto), HttpStatus.CREATED);
    }

    @PutMapping("smartphone/{smartphoneId}")
    public ResponseEntity updateSmartphoneById(@PathVariable("smartphoneId") UUID smartphoneId, @RequestBody @Validated SmartphoneDto smartphoneDto){
        return new ResponseEntity<>(smartphoneService.updateSmartphone(smartphoneId, smartphoneDto), HttpStatus.NO_CONTENT);
    }

}
