package de.fraunhofer.isst.dataspaceconnector.model.webhook.harbor;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractFactory;
import de.fraunhofer.isst.dataspaceconnector.utils.ErrorMessages;
import de.fraunhofer.isst.dataspaceconnector.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class RepositoryFactory implements AbstractFactory<Repository, RepositoryDesc> {

    @Override
    public Repository create(RepositoryDesc desc) {
        Utils.requireNonNull(desc, ErrorMessages.DESC_NULL);

        final var repo = new Repository();

        update(repo, desc);

        return repo;
    }

    @Override
    public boolean update(Repository entity, RepositoryDesc desc) {
        return false;
    }
}
