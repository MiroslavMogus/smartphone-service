package miro.springframework.smartphoneservice.repositories;

import miro.springframework.smartphoneservice.domain.Smartphone;
import miro.springframework.smartphoneservice.web.model.SmartphoneManufacturerEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 *
 */
public interface SmartphoneRepository extends JpaRepository<Smartphone, UUID> {
    Page<Smartphone> findAllBySmartphoneName(String smartphoneName, Pageable pageable);

    Page<Smartphone> findAllBySmartphoneManufacturer(SmartphoneManufacturerEnum smartphoneManufacturer, Pageable pageable);

    Page<Smartphone> findAllBySmartphoneNameAndSmartphoneManufacturer(String smartphoneName, SmartphoneManufacturerEnum smartphoneManufacturer, Pageable pageable);

    Smartphone findByUpc(String upc);
}
