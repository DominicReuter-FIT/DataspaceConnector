package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

public enum EventType {
    PUSH_ARTIFACT,
    PULL_ARTIFACT,
    DELETE_ARTIFACT,
    UPLOAD_CHART,
    DOWNLOAD_CHART,
    DELETE_CHART,
    SCANNING_COMPLETED,
    SCANNING_FAILED,
    QUOTA_EXCEED,
    QUOTA_WARNING,
    REPLICATION
}
