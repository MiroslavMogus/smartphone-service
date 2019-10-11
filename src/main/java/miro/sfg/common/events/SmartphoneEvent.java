package miro.sfg.common.events;

import miro.springframework.smartphoneservice.web.model.SmartphoneDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SmartphoneEvent implements Serializable {

    static final long serialVersionUID = -5781515597148163111L;

    private SmartphoneDto smartphoneDto;
}
