package de.fraunhofer.isst.dataspaceconnector.model.container.template;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class Environment extends AbstractEntity {

    /**
     * Serial version uid.
     **/
    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    public String name;

    @JsonProperty("label")
    public String label;

    @JsonProperty("description")
    public String description;

    @JsonProperty("default")
    public String defaultValue;

    @JsonProperty("preset")
    public boolean preset;

//    @JsonProperty("select")
//    public String select;

}
