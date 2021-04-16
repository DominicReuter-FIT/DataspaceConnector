package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractFactory;
import de.fraunhofer.isst.dataspaceconnector.utils.ErrorMessages;
import de.fraunhofer.isst.dataspaceconnector.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@NoArgsConstructor
public class EventDataFactory implements AbstractFactory<EventData, EventDataDesc> {


    @Override
    public EventData create(final EventDataDesc desc) {
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var eventData = new EventData();
        eventData.setRepository(new Repository());
        eventData.setResources(new ArrayList<>());

        update(eventData, desc);

        return eventData;
    }

    @Override
    public boolean update(EventData entity, EventDataDesc desc) {
        return false;
    }
}