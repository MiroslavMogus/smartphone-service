package miro.springframework.smartphoneservice.bootstrap;

import miro.springframework.smartphoneservice.domain.Smartphone;
import miro.springframework.smartphoneservice.repositories.SmartphoneRepository;
import miro.springframework.smartphoneservice.web.model.SmartphoneManufacturerEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 *
 */
@RequiredArgsConstructor
@Component
public class SmartphoneLoader implements CommandLineRunner {

    public static final String SMARTPHONE_1_UPC = "0631234200036";
    public static final String SMARTPHONE_2_UPC = "0631234300019";
    public static final String SMARTPHONE_3_UPC = "0083783375213";

    private final SmartphoneRepository smartphoneRepository;

    @Override
    public void run(String... args) throws Exception {

          if(smartphoneRepository.count() == 0 ) {
              loadSmartphoneObjects();
          }
    }

    private void loadSmartphoneObjects() {
        Smartphone b1 = Smartphone.builder()
                .smartphoneName("Iphone 11")
                .smartphoneManufacturer(SmartphoneManufacturerEnum.APPLE.name())
                .minOnHand(12)
                .quantityToAssemble(200)
                .price(new BigDecimal("12.95"))
                .upc(SMARTPHONE_1_UPC)
                .build();

        Smartphone b2 = Smartphone.builder()
                .smartphoneName("Galaxy S10")
                .smartphoneManufacturer(SmartphoneManufacturerEnum.SAMSUNG.name())
                .minOnHand(12)
                .quantityToAssemble(200)
                .price(new BigDecimal("12.95"))
                .upc(SMARTPHONE_2_UPC)
                .build();

        Smartphone b3 = Smartphone.builder()
                .smartphoneName("P30 Pro")
                .smartphoneManufacturer(SmartphoneManufacturerEnum.HUAWEI.name())
                .minOnHand(12)
                .quantityToAssemble(200)
                .price(new BigDecimal("12.95"))
                .upc(SMARTPHONE_3_UPC)
                .build();

        smartphoneRepository.save(b1);
        smartphoneRepository.save(b2);
        smartphoneRepository.save(b3);
    }
}
