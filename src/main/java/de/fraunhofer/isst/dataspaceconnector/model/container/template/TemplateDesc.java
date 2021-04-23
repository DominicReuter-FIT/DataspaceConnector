package de.fraunhofer.isst.dataspaceconnector.model.container.template;

import de.fraunhofer.isst.dataspaceconnector.model.AbstractDescription;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateDesc extends AbstractDescription<Template> {

    private int type;

    private String title;

    private String name;

    private String description;

    private URI logo;

    private String image;

    private URI registry;

    private String platform;

    private String restart_policy;

    private List<String> categories;

    private List<String> command;

    private Map<String, String> ports;

    private List<Volume> volumes;

    private List<Environment> environment;

    private List<Label> label;

}
