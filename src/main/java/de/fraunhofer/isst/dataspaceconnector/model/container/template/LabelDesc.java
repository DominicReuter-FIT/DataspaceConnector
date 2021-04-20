package de.fraunhofer.isst.dataspaceconnector.model.container.template;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractDescription;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LabelDesc extends AbstractDescription<Label> {

    public String label;

    public String value;

}
