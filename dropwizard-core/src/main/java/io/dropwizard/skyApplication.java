package io.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class skyApplication extends Application<skyConfiguration> {

    public static void main(final String[] args) throws Exception {
        new skyApplication().run(args);
    }

    @Override
    public String getName() {
        return "sky";
    }

    @Override
    public void initialize(final Bootstrap<skyConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final skyConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
