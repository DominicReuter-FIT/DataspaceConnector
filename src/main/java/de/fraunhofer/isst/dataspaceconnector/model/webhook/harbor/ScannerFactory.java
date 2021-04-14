package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractFactory;
import de.fraunhofer.isst.dataspaceconnector.utils.ErrorMessages;
import de.fraunhofer.isst.dataspaceconnector.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ScannerFactory implements AbstractFactory<Scanner, ScannerDesc> {

    @Override
    public Scanner create(ScannerDesc desc) {
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var scanner = new Scanner();

        update(scanner, desc);

        return scanner;
    }

    @Override
    public boolean update(Scanner entity, ScannerDesc desc) {
        return false;
    }
}
