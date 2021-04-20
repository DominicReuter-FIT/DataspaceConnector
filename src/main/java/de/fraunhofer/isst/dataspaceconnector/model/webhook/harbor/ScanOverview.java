package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.fraunhofer.isst.dataspaceconnector.model.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter(AccessLevel.PACKAGE)
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ScanOverview extends AbstractEntity {

    /**
     * Serial version uid.
     **/
    private static final long serialVersionUID = 1L;

    @JsonProperty("report_id")
    private UUID reportId;

    @JsonProperty("scan_status")
    private String scanStatus;

    @JsonProperty("severity")
    private String severity;

    @JsonProperty("duration")
    private int duration;

    @JsonProperty("complete_percent")
    private int completePercent;

    @JsonProperty("start_time")
    private ZonedDateTime startTime;

    @JsonProperty("end_time")
    private ZonedDateTime endTime;

    @OneToOne
    @JsonProperty("summary")
    private Summary summary;

    @OneToOne
    @JsonProperty("scanner")
    private Scanner scanner;
}
