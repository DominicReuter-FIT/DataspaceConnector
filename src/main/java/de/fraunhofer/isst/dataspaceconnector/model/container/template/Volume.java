package de.fraunhofer.isst.dataspaceconnector.model.container.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.fraunhofer.isst.dataspaceconnector.model.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.net.URI;

@Entity
@Table
@Getter
//@Setter(AccessLevel.PACKAGE)
@Setter
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class Volume extends AbstractEntity {

    /**
     * Serial version uid.
     **/
    private static final long serialVersionUID = 1L;

    @JsonProperty("container")
    private String container;

    @JsonProperty("bind")
    private String bind;

}
