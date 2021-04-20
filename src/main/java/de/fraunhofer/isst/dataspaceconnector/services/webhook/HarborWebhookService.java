package de.fraunhofer.isst.dataspaceconnector.services.webhook;


import de.fraunhofer.isst.dataspaceconnector.exceptions.WebhookException;
import de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor.Event;

public interface HarborWebhookService {

    void processHarborEvent(Event event) throws WebhookException;

}
