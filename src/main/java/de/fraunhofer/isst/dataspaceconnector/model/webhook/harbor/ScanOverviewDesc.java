package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractDescription;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScanOverviewDesc extends AbstractDescription<ScanOverview> {

    private UUID reportId;

    private String scanStatus;

    private String severity;

    private int duration;

    private int completePercent;

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;
}
