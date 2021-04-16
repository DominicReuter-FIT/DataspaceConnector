package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractFactory;
import de.fraunhofer.isst.dataspaceconnector.utils.ErrorMessages;
import de.fraunhofer.isst.dataspaceconnector.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ResourcesFactory implements AbstractFactory<Resources, ResourcesDesc> {

    @Override
    public Resources create(final ResourcesDesc desc) {
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var resource = new Resources();
        resource.setScanOverview(new ScanOverview());

        update(resource, desc);

        return resource;
    }

    @Override
    public boolean update(Resources entity, ResourcesDesc desc) {
        return false;
    }
}
