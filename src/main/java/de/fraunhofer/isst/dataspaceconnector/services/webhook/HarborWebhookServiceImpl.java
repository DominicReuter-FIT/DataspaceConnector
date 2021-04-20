package de.fraunhofer.isst.dataspaceconnector.services.webhook;

import de.fraunhofer.isst.dataspaceconnector.exceptions.ResourceNotFoundException;
import de.fraunhofer.isst.dataspaceconnector.exceptions.WebhookException;
import de.fraunhofer.isst.dataspaceconnector.model.*;
import de.fraunhofer.isst.dataspaceconnector.model.AppRepresentation;
import de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor.Event;
import de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor.EventDesc;
import de.fraunhofer.isst.dataspaceconnector.services.resources.ArtifactService;
import de.fraunhofer.isst.dataspaceconnector.services.resources.BaseEntityService;
import de.fraunhofer.isst.dataspaceconnector.services.resources.RepresentationService;
import de.fraunhofer.isst.dataspaceconnector.services.resources.ResourceService;
import de.fraunhofer.isst.dataspaceconnector.services.usagecontrol.PolicyDecisionService;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HarborWebhookServiceImpl extends BaseEntityService<Event, EventDesc> implements HarborWebhookService{

    /**
     * Class level logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyDecisionService.class);

    private final @NonNull ResourceService<OfferedResource, OfferedResourceDesc> resourceService;

    private final @NonNull RepresentationService representationService;

    private final @NonNull ArtifactService artifactService;

    @Override
    public void processHarborEvent(Event event) throws WebhookException {

        isValidEvent(event);

        switch (event.getType()) {
            case PUSH_ARTIFACT:
                this.pushArtifactEventHandler(event);
                break;
            case PULL_ARTIFACT:
                this.pullArtifactEventHandler(event);
                break;
            case DELETE_ARTIFACT:
                this.deleteArtifactEventHandler(event);
                break;
            case QUOTA_EXCEED:
            case QUOTA_WARNING:
            case DOWNLOAD_CHART:
            case UPLOAD_CHART:
            case DELETE_CHART:
                throw new WebhookException("Event type is not implemented yet");
            case SCANNING_COMPLETED:
                this.scanningCompletedEventHandler(event);
                break;
            case SCANNING_FAILED:
                this.scanningFailedEventHandler(event);
                break;
            case REPLICATION:
                // TODO: NO NEED TO IMPLEMENT NOW
                break;
            default:
                throw new WebhookException("The Event was not a regular harbor registry event!. Not a valid Event!");
        }
    }

    private void pushArtifactEventHandler(Event event) throws WebhookException {

        isValidEvent(event);

        // REPOSITORY
        final var created = event.getEventData().getRepository().getDateCreated();
        final Date dateCreated = Date.from(Instant.ofEpochMilli(created).atZone(ZoneId.systemDefault()).toInstant());

        final var repoName = event.getEventData().getRepository().getName();
        final var repoNamespace = event.getEventData().getRepository().getNamespace();
        final var repoFullName = event.getEventData().getRepository().getRepoFullName();
        final var repoType = event.getEventData().getRepository().getRepoType();

        // RESOURCE
        final var resources= event.getEventData().getResources();
        final var tag = "latest";
        var resource_url = "";
        var digest = "";
        for(var res : resources){
            if(res.getTag() == tag){
                resource_url = res.getResourceUrl();
                digest = res.getDigest();
            }
        }

        HashMap<String, UUID> ids = (HashMap<String, UUID>) extractIdsFromEvent(repoName);

        // GET REPRESENTATION
        if(representationService.doesExist(ids.get("representationId"))){
            var representationDesc = new RepresentationDesc();
            representationDesc.setDistributionService(URI.create(resource_url));
            representationDesc.setRuntimeEnvironment("docker");
            representationService.update(ids.get("representationId"), representationDesc);
        } else {

        }

        if(resourceService.doesExist(ids.get("appId"))){
            var app = new AppData();
            app.setAvailable(true);
            app.setNamespace("");

            ArtifactDesc artifactDesc = new ArtifactDesc();
            artifactDesc.setValue();

            artifactService.create()

        }
        var app = resourceService.get(ids.get("appId"));

        ArtifactDesc desc = new ArtifactDesc();
        desc.

        artifactService.create()


        resourceService.create()
        representation.s


        var backendApp = representation.getBackendApp();

        BackendAppContainer newBackendApp = new BackendAppContainer();
        newBackendApp.setCreated(dateCreated);
        newBackendApp.setModified(newBackendApp.getCreated());
        newBackendApp.setName(repoName);
        newBackendApp.setNamespace(repoNamespace);
        newBackendApp.setRepoFullName(repoFullName);
        newBackendApp.setRepoType(repoType);
        newBackendApp.setDigest(digest);
        newBackendApp.setUrl(URI.create(resource_url));
        newBackendApp.setAvailable(true);

        // backendApp.setLabel();
        // backendApp.setEnvironmentVariables();
        // backendApp.setStorageConfiguration();
        // backendApp.setOs();
        // backendApp.setArchitecture();

        if (backendApp == null) {
            representation.setBackendApp(newBackendApp);
        } else {
            // UPDATE EXISTING BACKENDAPP
            BackendAppContainer tmpbackendApp = (BackendAppContainer) backendApp;
            tmpbackendApp.setModified(dateCreated);
            tmpbackendApp.setAvailable(true);
        }

        appRepresentationService.updateRepresentation(ids.get("appId"), ids.get("representationId"), representation);
    }

    private void pullArtifactEventHandler(Event event) throws WebhookException {

    }

    private void deleteArtifactEventHandler(Event event) throws WebhookException {
        isValidEvent(event);

        final var repoName = event.getEventData().getRepository().getName();

        HashMap<String, UUID> ids = (HashMap<String, UUID>) extractIdsFromEvent(repoName);

        // DELETE REPRESENTATION FROM APP METADATA
        appRepresentationService.deleteRepresentation(ids.get("appId"), ids.get("representationId"));
    }

    private void scanningCompletedEventHandler(Event event) throws WebhookException{

        isValidEvent(event);

        if(event.getEventData().getResources()[0].getScanOverview() == null)
            throw new WebhookException("The Event ScanResult cannot be null. Not a valid SecurityScanEvent!");

        // REPOSITORY
        final var created = event.getEventData().getRepository().getDateCreated();
        final Date dateCreated = new Date(created);

        final var repoName = event.getEventData().getRepository().getName();
        final var repoNamespace = event.getEventData().getRepository().getNamespace();
        final var repoFullName = event.getEventData().getRepository().getRepoFullName();
        final var repoType = event.getEventData().getRepository().getRepoType();

        // RESOURCE
        final var resources= event.getEventData().getResources();
        final var tag = "latest";
        var resource_url = "";
        var digest = "";
//        for(var res : resources){
//            if(res.getTag() == tag){
//                resource_url = res.getResourceUrl();
//                digest = res.getDigest();
//            }
//        }

        final var scanOverview = resources[0].getScanOverview();

        HashMap<String, UUID> ids = (HashMap<String, UUID>) extractIdsFromEvent(repoName);

        // GET REPRESENTATION
        var representation = appRepresentationService.getRepresentation(ids.get("appId"), ids.get("representationId"));
        if(representation == null)
            throw new ResourceNotFoundException("App Representation cannot be found.");

        var backendApp = (BackendAppContainer)representation.getBackendApp();
        if(backendApp == null)
            throw new ResourceNotFoundException("App cannot be found.");

        SecurityScanSummaryDetail securityScanSummaryDetail = new SecurityScanSummaryDetail();
        securityScanSummaryDetail.setHigh(scanOverview.getSummary().getSummary().getHigh());
        securityScanSummaryDetail.setLow(scanOverview.getSummary().getSummary().getLow());
        securityScanSummaryDetail.setMedium(scanOverview.getSummary().getSummary().getMedium());

        SecurityScanSummary securityScanSummary = new SecurityScanSummary();
        securityScanSummary.setTotal(scanOverview.getSummary().getTotal());
        securityScanSummary.setFixable(scanOverview.getSummary().getFixable());
        securityScanSummary.setSummary(securityScanSummaryDetail);

        SecurityScannerInformation securityScannerInformation = new SecurityScannerInformation();
        securityScannerInformation.setVendor(scanOverview.getScanner().getVendor());
        securityScannerInformation.setVersion(scanOverview.getScanner().getVersion());
        securityScannerInformation.setName(scanOverview.getScanner().getName());

        List<SecurityScanResult> secresults = new ArrayList<>();

        SecurityScanResult securityScanResult = new SecurityScanResult();
        securityScanResult.setScanner(securityScannerInformation);
        securityScanResult.setCompletePercent(scanOverview.getCompletePercent());
        securityScanResult.setSummary(securityScanSummary);
        securityScanResult.setStartTime(scanOverview.getStartTime());
        securityScanResult.setEndTime(scanOverview.getEndTime());
        securityScanResult.setSeverity(scanOverview.getSeverity());
        securityScanResult.setReportId(scanOverview.getReportId());
        securityScanResult.setDuration(scanOverview.getDuration());
        securityScanResult.setScanStatus(scanOverview.getScanStatus());

        secresults.add(securityScanResult);

        backendApp.setSecurityScanResult(secresults);

        appRepresentationService.updateRepresentation(ids.get("appId"), ids.get("representationId"), representation);

    }

    private void scanningFailedEventHandler(Event event) throws WebhookException {

        isValidEvent(event);

        if(event.getEventData().getResources()[0].getScanOverview() == null)
            throw new WebhookException("The Event ScanResult cannot be null. Not a valid SecurityScanEvent!");

        // REPOSITORY
        final var created = event.getEventData().getRepository().getDateCreated();
        final Date dateCreated = new Date(created);

        final var repoName = event.getEventData().getRepository().getName();
        final var repoNamespace = event.getEventData().getRepository().getNamespace();
        final var repoFullName = event.getEventData().getRepository().getRepoFullName();
        final var repoType = event.getEventData().getRepository().getRepoType();

        // RESOURCE
        final var resources= event.getEventData().getResources();
        final var tag = "latest";
        var resource_url = "";
        var digest = "";
//        for(var res : resources){
//            if(res.getTag() == tag){
//                resource_url = res.getResourceUrl();
//                digest = res.getDigest();
//            }
//        }

        final var scanOverview = resources[0].getScanOverview();

        HashMap<String, UUID> ids = (HashMap<String, UUID>) extractIdsFromEvent(repoName);

        // GET REPRESENTATION
        var representation = appRepresentationService.getRepresentation(ids.get("appId"), ids.get("representationId"));
        if(representation == null)
            throw new ResourceNotFoundException("App Representation cannot be found.");

        var backendApp = (BackendAppContainer)representation.getBackendApp();
        if(backendApp == null)
            throw new ResourceNotFoundException("App cannot be found.");

        List<SecurityScanResult> secresults = new ArrayList<>();

        SecurityScanResult securityScanResult = new SecurityScanResult();
        securityScanResult.setReportId(scanOverview.getReportId());
        // STATUS IS "ERROR" WHEN SCAN FAILED
        securityScanResult.setScanStatus(scanOverview.getScanStatus());
        securityScanResult.setSeverity(scanOverview.getSeverity());
        securityScanResult.setStartTime(scanOverview.getStartTime());
        securityScanResult.setEndTime(scanOverview.getEndTime());
        securityScanResult.setDuration(scanOverview.getDuration());
        securityScanResult.setCompletePercent(scanOverview.getCompletePercent());
        securityScanResult.setSummary(null);

        secresults.add(securityScanResult);

        backendApp.setSecurityScanResult(secresults);

        appRepresentationService.updateRepresentation(ids.get("appId"), ids.get("representationId"), representation);
    }


    private Map<String, UUID> extractIdsFromEvent(String repoName) throws WebhookException, IllegalArgumentException{
        UUID appId;
        UUID representationId;
        try{
            final var incomingIds = repoName.split("_");
            final var appIdStr = incomingIds[0];
            final var representationIdStr = incomingIds[1];
            if((appIdStr.isEmpty() || appIdStr.isBlank()) || (representationIdStr.isEmpty() || representationIdStr.isBlank()))
                throw new IllegalArgumentException("Container Image name does not fit requirements: appId_representationId");
            appId = UUID.fromString(appIdStr);
            representationId = UUID.fromString(representationIdStr);
        } catch (ArrayIndexOutOfBoundsException e){
            throw new WebhookException("Container Image name does not fit requirements: appId_representationId");
        } catch (IllegalArgumentException e){
            throw new WebhookException("AppId or RepresentationId is not a valid UUID");
        }
        return Map.of("appId", appId, "representationId", representationId);
    }

    private boolean isValidEvent(Event event) throws WebhookException{
        if(event.type == null){
            throw new WebhookException("The Event Type cannot be null. Not a valid Event!");
        }
        if(event.getEventData() == null)
            throw new WebhookException("The Event Data cannot be null. Not a valid Event!");

        if(event.getEventData().getRepository() == null)
            throw new WebhookException("The Event Repository cannot be null. Not a valid Event!");

        if(event.getEventData().getResources() == null)
            throw new WebhookException("The Event Resources cannot be null. Not a valid Event!");

        return true;
    }

}