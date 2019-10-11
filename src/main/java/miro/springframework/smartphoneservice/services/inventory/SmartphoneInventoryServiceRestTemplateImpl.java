package miro.springframework.smartphoneservice.services.inventory;

import miro.springframework.smartphoneservice.services.inventory.model.SmartphoneInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 *
 */
@Slf4j
@ConfigurationProperties(prefix = "smartphone.factory", ignoreUnknownFields = false)
@Component
public class SmartphoneInventoryServiceRestTemplateImpl implements SmartphoneInventoryService {

    private final String INVENTORY_PATH = "/api/v1/smartphone/{smartphoneId}/inventory";
    private final RestTemplate restTemplate;

    private String smartphoneInventoryServiceHost;

    public void setSmartphoneInventoryServiceHost(String smartphoneInventoryServiceHost) {
        this.smartphoneInventoryServiceHost = smartphoneInventoryServiceHost;
    }

    public SmartphoneInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer getOnhandInventory(UUID smartphoneId) {

        log.debug("Calling Inventory Service");

        ResponseEntity<List<SmartphoneInventoryDto>> responseEntity = restTemplate
                .exchange(smartphoneInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<SmartphoneInventoryDto>>(){}, (Object) smartphoneId);

        //sum from inventory list
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(SmartphoneInventoryDto::getQuantityOnHand)
                .sum();

        return onHand;
    }
}
