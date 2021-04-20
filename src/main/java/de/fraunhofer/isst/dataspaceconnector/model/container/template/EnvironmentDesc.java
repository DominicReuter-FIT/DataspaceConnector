package de.fraunhofer.isst.dataspaceconnector.model.container.template;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractDescription;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EnvironmentDesc extends AbstractDescription<Environment> {

    public String name;

    public String label;

    public String defaultValue;

    public String description;

    public boolean preset;
}
