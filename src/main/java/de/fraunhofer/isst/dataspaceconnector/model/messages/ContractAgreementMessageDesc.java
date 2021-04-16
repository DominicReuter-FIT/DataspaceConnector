package de.fraunhofer.isst.dataspaceconnector.model.messages;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.URI;

/**
 * Class for all description request message parameters.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContractAgreementMessageDesc extends MessageDesc {

    /**
     * The ids of the correlation message.
     */
    private URI correlationMessage;

    /**
     * All args constructor.
     *
     * @param recipient          The recipient.
     * @param correlationMessage The correlation message.
     */
    public ContractAgreementMessageDesc(final URI recipient, final URI correlationMessage) {
        super(recipient);
        this.correlationMessage = correlationMessage;
    }
}
