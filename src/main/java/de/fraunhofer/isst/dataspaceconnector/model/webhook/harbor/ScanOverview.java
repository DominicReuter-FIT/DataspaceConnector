package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

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

    private UUID reportId;

    private String scanStatus;

    private String severity;

    private int duration;

    private int completePercent;

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    @OneToOne
    private Summary summary;

    @OneToOne
    private Scanner scanner;
}
