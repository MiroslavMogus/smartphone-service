package miro.springframework.smartphoneservice.services.assembling;

import miro.sfg.common.events.AssembleSmartphoneEvent;
import miro.sfg.common.events.NewInventoryEvent;
import miro.springframework.smartphoneservice.config.JmsConfig;
import miro.springframework.smartphoneservice.domain.Smartphone;
import miro.springframework.smartphoneservice.repositories.SmartphoneRepository;
import miro.springframework.smartphoneservice.web.model.SmartphoneDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AssembleSmartphoneListener {

    private final SmartphoneRepository smartphoneRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.ASSEMBLING_REQUEST_QUEUE)
    public void listen(AssembleSmartphoneEvent event){
        SmartphoneDto smartphoneDto = event.getSmartphoneDto();

        Smartphone smartphone = smartphoneRepository.getOne(smartphoneDto.getId());

        smartphoneDto.setQuantityOnHand(smartphone.getQuantityToAssemble());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(smartphoneDto);

        log.debug("Assembled smartphone " + smartphone.getMinOnHand() + " : QOH: " + smartphoneDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
