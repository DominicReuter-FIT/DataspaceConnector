package de.fraunhofer.isst.dataspaceconnector.model.container.template;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractFactory;
import de.fraunhofer.isst.dataspaceconnector.utils.ErrorMessages;
import de.fraunhofer.isst.dataspaceconnector.utils.MetadataUtils;
import de.fraunhofer.isst.dataspaceconnector.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@NoArgsConstructor
public class TemplateFactory implements AbstractFactory<Template, TemplateDesc> {


    private static final URI DEFAULT_LOGO = URI.create("binac.fit.fraunhofer.de");
    private static final String DEFAULT_NAME = "";
    private static final String DEFAULT_DESCRIPTION = "";
    private static final String DEFAULT_IMAGE = "";
    private static final String DEFAULT_PLATFORM = "linux";
    private static final URI DEFAULT_REGISTRY = URI.create("binac.fit.fraunhofer.de") ;
    private static final String DEFAULT_TITLE = "";
    private static final Integer DEFAULT_TYPE = 1;

    @Override
    public Template create(TemplateDesc desc) {
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var template = new Template();
        template.setVolumes(new ArrayList<>());
        template.setEnvironment(new ArrayList<>());
        template.setLabel(new ArrayList<>());

        update(template, desc);

        return template;
    }

    @Override
    public boolean update(Template template, TemplateDesc desc) {
        Utils.requireNonNull(template, ErrorMessages.ENTITY_NULL);
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var hasUpdatedType = this.updateType(template, desc.getType());
        final var hasUpdatedTitle = this.updateTitle(template, desc.getTitle());
        final var hasUpdatedName = this.updateName(template, desc.getName());
        final var hasUpdatedDescription = this.updateDescription(template, desc.getDescription());
        final var hasUpdatedLogo = this.updateLogo(template, desc.getLogo());
        final var hasUpdatedImage = this.updateImage(template, desc.getImage());
        final var hasUpdatedRegistry = this.updateRegistry(template, desc.getRegistry());
        final var hasUpdatedPlatform = this.updatePlatform(template, desc.getPlatform());
        final var hasUpdatedRestartPolicy = this.updateRestartPolicy(template, desc.getRestart_policy());
        final var hasUpdatedCategories = this.updateCategories(template, desc.getCategories());
        final var hasUpdatedCommand = this.updateCommands(template, desc.getCommand());
        final var hasUpdatedPorts = this.updatePorts(template, desc.getPorts());

        return hasUpdatedType || hasUpdatedTitle || hasUpdatedName || hasUpdatedDescription || hasUpdatedLogo || hasUpdatedImage
                || hasUpdatedRegistry || hasUpdatedPlatform || hasUpdatedRestartPolicy || hasUpdatedCategories || hasUpdatedCommand || hasUpdatedPorts;
    }

    private boolean updateType(Template template, Integer type){
        final var newInt = MetadataUtils.updateInteger(template.getType(), type, DEFAULT_TYPE);
        newInt.ifPresent(template::setType);
        return newInt.isPresent();
    }

    private boolean updateTitle(Template template, String title){
        final var newStr = MetadataUtils.updateString(template.getTitle(), title, DEFAULT_TITLE);
        newStr.ifPresent(template::setTitle);
        return newStr.isPresent();
    }

    private boolean updateName(Template template, String name){
        final var newStr = MetadataUtils.updateString(template.getName(), name, DEFAULT_NAME);
        newStr.ifPresent(template::setName);
        return newStr.isPresent();
    }

    private boolean updateDescription(Template template, String description){
        final var newStr = MetadataUtils.updateString(template.getDescription(), description, DEFAULT_DESCRIPTION);
        newStr.ifPresent(template::setDescription);
        return newStr.isPresent();
    }

    private boolean updateImage(Template template, String image){
        final var newStr = MetadataUtils.updateString(template.getImage(), image, DEFAULT_IMAGE);
        newStr.ifPresent(template::setImage);
        return newStr.isPresent();
    }

    private boolean updatePlatform(Template template, String platform){
        final var newStr = MetadataUtils.updateString(template.getPlatform(), platform, DEFAULT_PLATFORM);
        newStr.ifPresent(template::setPlatform);
        return newStr.isPresent();
    }

    private boolean updateRestartPolicy(Template template, String restartPolicy){
        final var newStr = MetadataUtils.updateString(template.getRestart_policy(), restartPolicy, DEFAULT_PLATFORM);
        newStr.ifPresent(template::setRestart_policy);
        return newStr.isPresent();
    }

    private boolean updateCommands(Template template, List<String> commands){
        final var newStr = MetadataUtils.updateStringList(template.getCommand(), commands, new ArrayList<>());
        newStr.ifPresent(template::setCommand);
        return newStr.isPresent();
    }

    private boolean updateCategories(Template template, List<String> categories){
        final var newList = MetadataUtils.updateStringList(template.getCategories(), categories, new ArrayList<>());
        newList.ifPresent(template::setCategories);
        return newList.isPresent();
    }

    private boolean updatePorts(Template template, Map<String, String> ports){
        final var newMap = MetadataUtils.updateStringMap(template.getPorts(), ports, new HashMap<>());
        newMap.ifPresent(template::setPorts);
        return newMap.isPresent();
    }

    private boolean updateRegistry(Template template, URI registry){
        final var newURI = MetadataUtils.updateUri(template.getRegistry(), registry, DEFAULT_REGISTRY);
        newURI.ifPresent((template::setRegistry));
        return newURI.isPresent();
    }

    private boolean updateLogo(Template template, URI logo){
        final var newURI = MetadataUtils.updateUri(template.getLogo(), logo, DEFAULT_LOGO);
        newURI.ifPresent((template::setLogo));
        return newURI.isPresent();
    }



}
