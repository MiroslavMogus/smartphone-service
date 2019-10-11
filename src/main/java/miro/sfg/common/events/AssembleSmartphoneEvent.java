package miro.sfg.common.events;

import miro.springframework.smartphoneservice.web.model.SmartphoneDto;
import lombok.NoArgsConstructor;

/**
 *
 */
@NoArgsConstructor
public class AssembleSmartphoneEvent extends SmartphoneEvent {

    public AssembleSmartphoneEvent(SmartphoneDto smartphoneDto) {
        super(smartphoneDto);
    }
}
