package de.fraunhofer.isst.dataspaceconnector.services.template;

import de.fraunhofer.isst.dataspaceconnector.model.AppData;
import de.fraunhofer.isst.dataspaceconnector.model.Resource;
import de.fraunhofer.isst.dataspaceconnector.model.container.template.*;
import de.fraunhofer.isst.dataspaceconnector.services.resources.BaseEntityService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@NoArgsConstructor
public class ContainerTemplateService extends BaseEntityService<Template, TemplateDesc> {

    /**
     * Class level logger.
     */
    static final Logger LOGGER = LoggerFactory.getLogger(ContainerTemplateService.class);

    private TemplateDesc createContainerTemplate(Resource app, AppData appData) {

        TemplateDesc template = new TemplateDesc();

        //  Template Type 1 for Docker Container / 2 for Docker Swarm
        template.setType(1);

        // This is what is displayed when in the apps list page. Punctuation is nice here and adds to the polish
        template.setTitle(app.getTitle());

        // This is what the actual container is named. Should be appId_representationId
        template.setName(appData.getName());

        // Image Name
        /* "image": "linuxserver/headphones:latest" */
        template.setImage(appData.getRepoFullName());

        // Registry
        template.setRegistry(URI.create(String.format("%s/%s", appData.getUrl(), appData.getRepoFullName())));

        // Get description
        template.setDescription(app.getDescription());

        // LOGO
        try {
            template.setLogo(new URI("https://logo_placeholder.example"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        /* Categories */
        template.setCategories(app.getKeywords());

        /* Platform = linux */
        if(!appData.getOs().isEmpty() && !appData.getOs().isBlank()){
            template.setPlatform("linux");
        } else {
            template.setPlatform(appData.getOs());
        }

        /* Restart_Policy */
        template.setRestart_policy("always");

        // PORTS
        /*
        {
            "ports": ["8080:80/tcp", "443/tcp"]
        }
        // Labeled Ports
        {
          "ports": [
            {
              "WebUI": "8096:8096/tcp",
              "HTTPS WebUI": "8920:8920/tcp",
              "DNLA": "1900:1900/udp",
              "Discovery": "7359:7359/udp"
            }
          ],
        }
         */

        // VOLUMES
        /*
        {
          "volumes": [
            {
              "container": "/data",
              "bind": "!downloads"
            },
            {
              "container": "/etc/localtime",
              "bind": "/etc/localtime"
            },
              "container": "/config",
              "bind": "app_config"
          ],
        }
         */

        // Environment Variables
        // {
        //  "name": "the name of the environment variable, as supported in the container image (mandatory)",
        //  "label": "label for the input in the UI (mandatory unless set is present)",
        //  "description": "a short description for this input, will be available as a tooltip in the UI (optional)",
        //  "default": "default value associated to the variable (optional)",
        //  "preset": "boolean. If set to true, the UI will not generate an input (optional)",
        //  "select": "an array of possible values, will generate a select input (optional)"
        // }

        // PARSING PORTS AND VOLUMES AND ENVIRONMENT VARIABLES
        Map<String, String> ports = new HashMap<>();
        ArrayList<Volume> volumes = new ArrayList<>();
        ArrayList<Environment> environmentVariables = new ArrayList<>();

        // LABELS
        /*
        {
          "labels": [
            { "name": "com.example.vendor", "value": "Acme" },
            { "name": "com.example.license", "value": "GPL" },
            { "name": "com.example.version", "value": "1.0" }
          ]
        }
         */
        ArrayList<Label> labels = new ArrayList<>();

        if(app.getLicence() != null){
            var label = new Label();
            label.setLabel("license");
            label.setValue(app.getLicence().toString());
            labels.add(label);
        }

        if(app.getSovereign() != null){
            var label = new Label();
            label.setLabel("Author");
            label.setValue(app.getSovereign().toString());
            labels.add(label);
        }

        for(var appEndpoint : appData.getEndpoints()){
            String port_label = appEndpoint.getType().name();
            int port = appEndpoint.getPort();
            var portStr = String.format("%s:%s/%s", port, port, appEndpoint.getProtocol());
            if(!ports.containsValue(portStr))
                ports.put(port_label, portStr);
            }
            if(ports.size() == 1){
                var keys = ports.keySet().toArray();
                var value = ports.get(keys[0]);
                    ports.remove(keys[0]);
                    ports.put("IDS", value);
            }
                var volumeKeys = appData.getStorageConfiguration().keySet();
                if(!volumeKeys.isEmpty()){
                    for(var key : volumeKeys){
                        var value = appData.getStorageConfiguration().get(key);
                        var volume = new Volume();
                        volume.setContainer(key);
                        volume.setBind(value);
                        volumes.add(volume);
                    }
                }
                var envKeys = appData.getEnvironmentVariables().keySet();
                if(!envKeys.isEmpty()){
                    for(var key : envKeys){
                        var value = appData.getEnvironmentVariables().get(key);
                        var environment = new Environment();
                        environment.setName(key);
                        environment.setLabel(key);
                        environment.setDescription(key);
                        environment.setDefaultValue(value);
                        environment.setPreset(true);
                        environmentVariables.add(environment);
                    }
                }
                var labelKeys = appData.getLabels().keySet();
                if(!labelKeys.isEmpty()){
                    for(var key : labelKeys){
                        var value = appData.getLabels().get(key);
                        var label = new Label();
                        label.setLabel(key);
                        label.setValue(value);
                        labels.add(label);
                    }
                }

        template.setPorts(ports);
        template.setVolumes(volumes);
        template.setEnvironment(environmentVariables);

        return template;
    }


    /**
        HELPER
     */
    private String createDockerCompliantImageName(String containerName){
        // Replace Whitespaces with underlines
        containerName = containerName.replaceAll("\\s", "_");
        String allowedChars = "[a-zA-z0-9][a-zA-Z0-9_.-]";
        Pattern pattern = Pattern.compile("^" + allowedChars + "+$");
        Matcher matcher = pattern.matcher(containerName);
        if(matcher.find()){
            return containerName;
        } else {
            return "";
        }
    }

}
