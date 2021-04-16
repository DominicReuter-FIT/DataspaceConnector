package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractDescription;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class RepositoryDesc extends AbstractDescription<Repository> {

    private Date dateCreated;

    private String name;

    private String namespace;

    private String repoFullName;

    private String repoType;

}
