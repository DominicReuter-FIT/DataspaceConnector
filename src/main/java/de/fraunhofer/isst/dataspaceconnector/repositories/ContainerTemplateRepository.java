package de.fraunhofer.isst.dataspaceconnector.repositories;

import de.fraunhofer.isst.dataspaceconnector.model.container.template.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerTemplateRepository extends JpaRepository<Template, Long> {
}
