package de.fraunhofer.isst.dataspaceconnector.graphql;


import de.fraunhofer.iais.eis.BaseConnector;
import de.fraunhofer.iais.eis.DynamicAttributeToken;
import de.fraunhofer.iais.eis.Resource;
import de.fraunhofer.isst.dataspaceconnector.services.ids.ConnectorService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
public class ConnectorQueryResolver implements GraphQLQueryResolver {

    private ConnectorService connectorService;

    @Autowired
    public ConnectorQueryResolver(ConnectorService connectorService){
        this.connectorService = connectorService;
    }

    public String getConnectorWithoutResources(){
        return this.connectorService.getConnectorWithoutResources().toRdf();
    }

    public BaseConnector getConnectorWithOfferedResources(){
        return this.connectorService.getConnectorWithOfferedResources();
    }

    public String getOfferedResourceById(URI resourceId){
        return this.connectorService.getOfferedResourceById(resourceId).toRdf();
    }

    public String getOutboundModelVersion(){
        return this.connectorService.getOutboundModelVersion();
    }

    public List<? extends String> getInboundModelVersion(){
        return this.connectorService.getInboundModelVersion();
    }

    public String getCurrentDat(){
        return this.connectorService.getCurrentDat().toRdf();
    }

    public URI getConnectorId(){
        return this.connectorService.getConnectorId();
    }




}
