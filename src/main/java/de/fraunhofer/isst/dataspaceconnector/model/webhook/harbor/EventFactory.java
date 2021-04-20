package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractFactory;
import de.fraunhofer.isst.dataspaceconnector.utils.ErrorMessages;
import de.fraunhofer.isst.dataspaceconnector.utils.MetadataUtils;
import de.fraunhofer.isst.dataspaceconnector.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@NoArgsConstructor
public class EventFactory implements AbstractFactory<Event, EventDesc> {

    static final ZonedDateTime DEFAULT_OCCURAT = ZonedDateTime.now(ZoneId.systemDefault());
    static final String DEFAULT_OPERATOR = "";

    @Override
    public Event create(final EventDesc desc) {
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var event = new Event();
        event.setEventData(new EventData());

        update(event, desc);

        return event;
    }

    @Override
    public boolean update(final Event event, final EventDesc desc) {
        return false;
    }

}
