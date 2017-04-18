package com.eter.spark.app.template;

import com.eter.spark.app.template.exceptions.MissingApplicationDescriptorException;
import com.eter.spark.app.template.exceptions.MissingEnvironmentException;
import com.eter.spark.app.template.exceptions.NoConfigurationException;
import com.eter.spark.app.template.exceptions.SparkAppException;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession;

/**
 * Created by abosii on 4/18/2017.
 */
public abstract class SparkSessionInitializer {
    private Configuration configuration;
    private SparkContext sparkContext;
    private SparkSession sparkSession;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public SparkContext getSparkContext() {
        return sparkContext;
    }

    public SparkSession getSparkSession() {
        return sparkSession;
    }

    public void run() throws SparkAppException {
        init();
    }

    protected void init() throws SparkAppException {
        if (configuration == null)
            throw new NoConfigurationException("Configuration is missing");

        if (configuration.getApplicationDescriptor() == null)
            throw new MissingApplicationDescriptorException();

        if (configuration.getEnvironment() == null)
            throw new MissingEnvironmentException();

        SparkSession.Builder builder = new SparkSession.Builder();

        useApplicationDescriptor(builder);
        useEnvironment(builder);

        sparkSession = builder.getOrCreate();
        sparkContext = sparkSession.sparkContext();
    }

    private void useApplicationDescriptor(SparkSession.Builder builder) {
        ApplicationDescriptor descriptor = configuration.getApplicationDescriptor();

        builder.appName(descriptor.getName());
    }

    private void useEnvironment(SparkSession.Builder builder) {
        Environment environment = configuration.getEnvironment();
        builder.master(environment.getSparkMasterUrl());
        builder.config("hive.metastore.uris", environment.getHiveMetastoreUrl());

    }
}
