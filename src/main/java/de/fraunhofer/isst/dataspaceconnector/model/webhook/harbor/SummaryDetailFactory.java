package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractFactory;
import de.fraunhofer.isst.dataspaceconnector.utils.ErrorMessages;
import de.fraunhofer.isst.dataspaceconnector.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class SummaryDetailFactory implements AbstractFactory<SummaryDetail, SummaryDetailDesc> {

    @Override
    public SummaryDetail create(SummaryDetailDesc desc) {
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var summaryDetail = new SummaryDetail();

        update(summaryDetail, desc);

        return summaryDetail;
    }

    @Override
    public boolean update(SummaryDetail entity, SummaryDetailDesc desc) {
        return false;
    }
}
