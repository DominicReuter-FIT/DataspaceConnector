package de.fraunhofer.isst.dataspaceconnector.model.container.template;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractFactory;
import de.fraunhofer.isst.dataspaceconnector.utils.ErrorMessages;
import de.fraunhofer.isst.dataspaceconnector.utils.MetadataUtils;
import de.fraunhofer.isst.dataspaceconnector.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EnvironmentFactory implements AbstractFactory<Environment, EnvironmentDesc> {


    private static final String DEFAULT_DEFAULTVALUE = "";
    private static final String DEFAULT_LABEL = "";
    private static final String DEFAULT_NAME = "";
    private static final String DEFAULT_DESCRIPTION = "";

    @Override
    public Environment create(EnvironmentDesc desc) {
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var environment = new Environment();

        update(environment, desc);

        return environment;
    }

    @Override
    public boolean update(Environment environment, EnvironmentDesc desc)
    {
        Utils.requireNonNull(environment, ErrorMessages.ENTITY_NULL);
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var hasUpdatedName = this.updateName(environment, desc.getName());
        final var hasUpdatedLabel = this.updateLabel(environment, desc.getLabel());
        final var hasUpdatedDefaultValue = this.updatedefaultValue(environment, desc.getDefaultValue());
        final var hasUpdatedDescription = this.updateDescription(environment, desc.getDescription());
        final var hasUpdatedPreset = this.updatePreset(environment, desc.isPreset());

        return hasUpdatedName || hasUpdatedLabel || hasUpdatedDefaultValue || hasUpdatedDescription || hasUpdatedPreset;
    }

    private boolean updatedefaultValue(Environment environment, String defaultValue) {
        final var newStr = MetadataUtils.updateString(environment.getDefaultValue(), defaultValue, DEFAULT_DEFAULTVALUE);
        newStr.ifPresent(environment::setDefaultValue);
        return newStr.isPresent();
    }

    private boolean updateLabel(Environment environment, String label) {
        final var newStr = MetadataUtils.updateString(environment.getLabel(), label, DEFAULT_LABEL);
        newStr.ifPresent(environment::setLabel);
        return newStr.isPresent();
    }

    private boolean updateName(Environment environment, String name) {
        final var newStr = MetadataUtils.updateString(environment.getName(), name, DEFAULT_NAME);
        newStr.ifPresent(environment::setName);
        return newStr.isPresent();
    }

    private boolean updateDescription(Environment environment, String description) {
        final var newStr = MetadataUtils.updateString(environment.getDescription(), description, DEFAULT_DESCRIPTION);
        newStr.ifPresent(environment::setDescription);
        return newStr.isPresent();
    }

    private boolean updatePreset(Environment environment, boolean preset) {
        if(environment.isPreset() != preset){
            environment.setPreset(preset);
            return true;
        }
        return false;
    }


}
