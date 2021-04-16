package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractFactory;
import de.fraunhofer.isst.dataspaceconnector.utils.ErrorMessages;
import de.fraunhofer.isst.dataspaceconnector.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class SummaryFactory implements AbstractFactory<Summary, SummaryDesc> {

    @Override
    public Summary create(SummaryDesc desc) {
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var summary = new Summary();
        summary.setSummary(new SummaryDetail());

        update(summary, desc);

        return summary;
    }

    @Override
    public boolean update(Summary entity, SummaryDesc desc) {
        return false;
    }
}
