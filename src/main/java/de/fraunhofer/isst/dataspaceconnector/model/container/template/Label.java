package de.fraunhofer.isst.dataspaceconnector.model.container.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.fraunhofer.isst.dataspaceconnector.model.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
//@Setter(AccessLevel.PACKAGE)
@Setter
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class Label extends AbstractEntity {

    /**
     * Serial version uid.
     **/
    private static final long serialVersionUID = 1L;

    @JsonProperty("label")
    private String label;

    @JsonProperty("value")
    private String value;


}