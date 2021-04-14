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
//        Utils.requireNonNull(event, ErrorMessages.ENTITY_NULL);
//        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);
//
//        final var hasUpdatedOccurAt = this.updateOccurAt(event, desc.getOccurAt());
//        final var hasUpdatedOperator = this.updateOperator(event, desc.getOperator());
//        final var hasUpdatedType = this.updateType(event, desc.getType());
//
//        return hasUpdatedOccurAt || hasUpdatedOperator || hasUpdatedType;
        return false;
    }

//    private boolean updateOccurAt(final Event event, final ZonedDateTime occurAt) {
//        final var newOccurAt = MetadataUtils.updateDate(event.getOccurAt(), occurAt, DEFAULT_OCCURAT);
//        newOccurAt.ifPresent(event::setOccurAt);
//
//        return newOccurAt.isPresent();
//
//    }
//
//    private boolean updateOperator(final Event event, final String operator) {
//        final var newOperator = MetadataUtils.updateString(event.getOperator(), operator, DEFAULT_OPERATOR);
//        newOperator.ifPresent(event::setOperator);
//
//        return newOperator.isPresent();
//    }
//
//    private boolean updateType(final Event event, EventType type) {
////        final var newEventType = MetadataUtils.
////        newEventType.isPresent(event::setType);
////
////        return newEventType.isPresent();
//    }
}
