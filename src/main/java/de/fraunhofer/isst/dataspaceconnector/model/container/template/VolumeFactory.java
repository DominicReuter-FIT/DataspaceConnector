package de.fraunhofer.isst.dataspaceconnector.model.container.template;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractFactory;
import de.fraunhofer.isst.dataspaceconnector.utils.ErrorMessages;
import de.fraunhofer.isst.dataspaceconnector.utils.MetadataUtils;
import de.fraunhofer.isst.dataspaceconnector.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class VolumeFactory implements AbstractFactory<Volume, VolumeDesc> {

    /**
     * The default remote id.
     */
    static final String DEFAULT_CONTAINER = "app";
    static final String DEFAULT_BIND = "/var/data";

    @Override
    public Volume create(VolumeDesc desc) {
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var volume = new Volume();

        update(volume, desc);

        return volume;
    }

    @Override
    public boolean update(Volume volume, VolumeDesc desc) {
        Utils.requireNonNull(volume, ErrorMessages.ENTITY_NULL);
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var hasUpdatedContainer = this.updateContainer(volume, desc.getContainer());
        final var hasUpdatedBind = this.updateBind(volume, desc.getBind());

        return hasUpdatedContainer || hasUpdatedBind;
    }

    private boolean updateContainer(Volume volume, String container){
        final var newStr = MetadataUtils.updateString(volume.getContainer(), container, DEFAULT_CONTAINER);
        newStr.ifPresent(volume::setContainer);
        return newStr.isPresent();
    }

    private boolean updateBind(Volume volume, String bind){
        final var newStr = MetadataUtils.updateString(volume.getBind(), bind, DEFAULT_BIND);
        newStr.ifPresent(volume::setBind);
        return newStr.isPresent();
    }

}
