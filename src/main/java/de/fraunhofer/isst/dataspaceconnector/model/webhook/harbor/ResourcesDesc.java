package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractDescription;

public class ResourcesDesc extends AbstractDescription<Resources> {

    private String digest;

    private String tag;

    private String resourceUrl;

}
