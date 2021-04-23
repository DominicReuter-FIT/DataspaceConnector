package de.fraunhofer.isst.dataspaceconnector.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter(AccessLevel.PACKAGE)
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class AppEndpoint extends AbstractEntity{

    /**
     * Serial version uid.
     **/
    private static final long serialVersionUID = 1L;

    private String information;

    private String documentation;

    private String mediaType;

    private AppEndpointType type;

    private String protocol;

    private int port;

    private String path;

}


