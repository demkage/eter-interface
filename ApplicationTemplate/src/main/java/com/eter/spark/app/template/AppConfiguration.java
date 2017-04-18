package com.eter.spark.app.template;

/**
 * Created by abosii on 4/18/2017.
 */
public class AppConfiguration {
    private ApplicationDescriptor applicationDescriptor;
    private Environment environment;


    public AppConfiguration(Environment environment, ApplicationDescriptor applicationDescriptor) {
        this.environment = environment;
        this.applicationDescriptor = applicationDescriptor;
    }

    public ApplicationDescriptor getApplicationDescriptor() {
        return applicationDescriptor;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
