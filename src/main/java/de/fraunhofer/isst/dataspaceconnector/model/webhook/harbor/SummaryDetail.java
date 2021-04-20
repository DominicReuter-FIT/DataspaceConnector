package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.fraunhofer.isst.dataspaceconnector.model.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter(AccessLevel.PACKAGE)
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class SummaryDetail extends AbstractEntity {

    /**
     * Serial version uid.
     **/
    private static final long serialVersionUID = 1L;

    @JsonProperty("unknown")
    private int unknown;

    @JsonProperty("Negligible")
    private int negligible;

    @JsonProperty("Low")
    private int low;

    @JsonProperty("Medium")
    private int medium;

    @JsonProperty("High")
    private int high;

    @JsonProperty("Critical")
    private int critical;

}
