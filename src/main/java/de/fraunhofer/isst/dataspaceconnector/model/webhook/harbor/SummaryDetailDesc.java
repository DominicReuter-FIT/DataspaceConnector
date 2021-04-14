package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractDescription;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SummaryDetailDesc extends AbstractDescription<SummaryDetail> {

    private int unknown;

    private int negligible;

    private int low;

    private int medium;

    private int high;

    private int critical;
}
