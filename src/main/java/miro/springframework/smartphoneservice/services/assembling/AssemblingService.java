package miro.springframework.smartphoneservice.services.assembling;

import miro.sfg.common.events.AssembleSmartphoneEvent;
import miro.springframework.smartphoneservice.config.JmsConfig;
import miro.springframework.smartphoneservice.domain.Smartphone;
import miro.springframework.smartphoneservice.repositories.SmartphoneRepository;
import miro.springframework.smartphoneservice.services.inventory.SmartphoneInventoryService;
import miro.springframework.smartphoneservice.web.mappers.SmartphoneMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AssemblingService {
    private final SmartphoneRepository smartphoneRepository;
    private final SmartphoneInventoryService smartphoneInventoryService;
    private final JmsTemplate jmsTemplate;
    private final SmartphoneMapper smartphoneMapper;

    @Scheduled(fixedRate = 5000) //every 5 seconds
    public void checkForLowInventory(){
        List<Smartphone> smartphones = smartphoneRepository.findAll();

        smartphones.forEach(smartphone -> {
            Integer invQOH = smartphoneInventoryService.getOnhandInventory(smartphone.getId());

            log.debug("Min Onhand is: " + smartphone.getMinOnHand());
            log.debug("Inventory is: "  + invQOH);

            if(smartphone.getMinOnHand() >= invQOH){
                jmsTemplate.convertAndSend(JmsConfig.ASSEMBLING_REQUEST_QUEUE, new AssembleSmartphoneEvent(smartphoneMapper.smartphoneToSmartphoneDto(smartphone)));
            }
        });

    }
}
