package de.fraunhofer.isst.dataspaceconnector.graphql;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractEntity;
import de.fraunhofer.isst.dataspaceconnector.services.EntityResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class EntityQueryResolver implements GraphQLQueryResolver {

    private EntityResolver entityResolver;

    @Autowired
    public EntityQueryResolver(EntityResolver entityResolver){
        this.entityResolver = entityResolver;
    }

    public AbstractEntity getEntityById(final URI elementId){
        return this.entityResolver.getEntityById(elementId);
    }
}