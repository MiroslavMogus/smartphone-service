package miro.sfg.common.events;

import miro.springframework.smartphoneservice.web.model.SmartphoneDto;
import lombok.NoArgsConstructor;

/**
 *
 */
@NoArgsConstructor
public class NewInventoryEvent extends SmartphoneEvent {
    public NewInventoryEvent(SmartphoneDto smartphoneDto) {
        super(smartphoneDto);
    }
}
