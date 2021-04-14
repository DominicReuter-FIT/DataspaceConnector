package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractFactory;
import de.fraunhofer.isst.dataspaceconnector.utils.ErrorMessages;
import de.fraunhofer.isst.dataspaceconnector.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ScannerOverviewFactory implements AbstractFactory<ScanOverview, ScanOverviewDesc> {

    @Override
    public ScanOverview create(ScanOverviewDesc desc) {
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var scanOverview = new ScanOverview();
        scanOverview.setScanner(new Scanner());

        update(scanOverview, desc);

        return scanOverview;
    }

    @Override
    public boolean update(ScanOverview entity, ScanOverviewDesc desc) {
        return false;
    }
}
