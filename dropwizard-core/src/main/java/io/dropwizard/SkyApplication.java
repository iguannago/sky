package io.dropwizard;

import io.dropwizard.health.TemplateHealthCheck;
import io.dropwizard.resources.HelloWorldResource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SkyApplication extends Application<SkyConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SkyApplication().run(args);
    }

    @Override
    public String getName() {
        return "sky";
    }

    @Override
    public void initialize(final Bootstrap<SkyConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final SkyConfiguration configuration,
                    final Environment environment) {
        final HelloWorldResource helloWorldResource = new HelloWorldResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );
        environment.jersey().register(helloWorldResource);

        final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.jersey().register(templateHealthCheck);
    }

}
