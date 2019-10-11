package miro.springframework.smartphoneservice.services.inventory;

import java.util.UUID;

/**
 *
 */
public interface SmartphoneInventoryService {

    Integer getOnhandInventory(UUID smartphoneId);
}
