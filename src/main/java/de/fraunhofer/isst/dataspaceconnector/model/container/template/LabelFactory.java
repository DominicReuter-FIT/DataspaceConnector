package de.fraunhofer.isst.dataspaceconnector.model.container.template;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractFactory;
import de.fraunhofer.isst.dataspaceconnector.utils.ErrorMessages;
import de.fraunhofer.isst.dataspaceconnector.utils.MetadataUtils;
import de.fraunhofer.isst.dataspaceconnector.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class LabelFactory implements AbstractFactory<Label, LabelDesc> {


    private static final String DEFAULT_LABEL = "APPSTORE";
    private static final String DEFAULT_VALUE = "FIT-FRAUNHOFER";

    @Override
    public Label create(LabelDesc desc) {
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var label = new Label();

        update(label, desc);

        return label;
    }

    @Override
    public boolean update(Label label, LabelDesc desc) {
        Utils.requireNonNull(label, ErrorMessages.ENTITY_NULL);
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var hasUpdatedLabel = this.updateLabel(label, desc.getLabel());
        final var hasUpdatedValue = this.updateValue(label, desc.getValue());

        return hasUpdatedLabel || hasUpdatedValue;
    }

    private boolean updateLabel(Label label, String value){
        final var newStr = MetadataUtils.updateString(label.getLabel(), value, DEFAULT_LABEL);
        newStr.ifPresent(label::setLabel);

        return newStr.isPresent();
    }

    private boolean updateValue(Label label, String value){
        final var newStr = MetadataUtils.updateString(label.getValue(), value, DEFAULT_VALUE);
        newStr.ifPresent(label::setValue);

        return newStr.isPresent();
    }
}