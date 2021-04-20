package de.fraunhofer.isst.dataspaceconnector.model;

import de.fraunhofer.isst.dataspaceconnector.model.container.template.Template;
import de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor.ScanOverview;
import lombok.*;

import javax.persistence.*;
import java.net.URI;
import java.util.List;
import java.util.Map;

@Entity
@Table
@Getter
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
//@Setter(AccessLevel.PACKAGE)
@Setter
public class AppData extends Data {

    private boolean available;

    private String namespace;

    private String repoFullName;

    private String repoType;

    private String name;

    private String digest;

    private String architecture;

    private String os;

    private URI url;

    private String documentation;

    @OneToOne
    private Template template;

    @ElementCollection
    private List<String> tags;

    @ElementCollection
    private Map<String, String> environmentVariables;

    @ElementCollection
    private Map<String, String> storageConfiguration;

    @ManyToMany
    private List<AppEndpoint> endpoints;

    @ManyToMany
    private List<ScanOverview> securityScanResult;

}