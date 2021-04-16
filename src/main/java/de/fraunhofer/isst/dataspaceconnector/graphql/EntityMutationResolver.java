package de.fraunhofer.isst.dataspaceconnector.graphql;

import de.fraunhofer.isst.dataspaceconnector.services.EntityUpdateService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityMutationResolver implements GraphQLMutationResolver {

    private EntityUpdateService entityUpdateService;

    @Autowired
    public EntityMutationResolver(EntityUpdateService entityUpdateService){
        this. entityUpdateService = entityUpdateService;
    }



}