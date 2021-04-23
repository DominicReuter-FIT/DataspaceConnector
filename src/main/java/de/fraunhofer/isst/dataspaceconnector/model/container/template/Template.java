package de.fraunhofer.isst.dataspaceconnector.model.container.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.fraunhofer.isst.dataspaceconnector.model.AbstractEntity;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Map;

@Entity
@Table
@Getter
@Setter(AccessLevel.PACKAGE)
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class Template extends AbstractEntity {

    /**
     * Serial version uid.
     **/
    private static final long serialVersionUID = 1L;

    @JsonProperty(required = true)
    private int type;

    @JsonProperty(required = true)
    private String title;

    @JsonProperty("name")
    private String name;

    @JsonProperty(required = true)
    private String description;

    @JsonProperty("logo")
    private URI logo;

    @JsonProperty(required = true)
    private String image;

    @JsonProperty("registry")
    private URI registry;

    @JsonProperty("platform")
    private String platform;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String restart_policy = "always";

    @ElementCollection
    @JsonProperty("categories")
    private List<String> categories;

    @ElementCollection
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> command;

    @ElementCollection
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> ports;


    @OneToMany
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Volume> volumes;

    @OneToMany
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("env")
    private List<Environment> environment;

    @OneToMany
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Label> label;

}
