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
    private AppConfiguration appConfiguration;
    private SparkContext sparkContext;
    private SparkSession sparkSession;

    public SparkSessionInitializer(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    public void setAppConfiguration(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
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
        if (appConfiguration == null)
            throw new NoConfigurationException("AppConfiguration is missing");

        if (appConfiguration.getApplicationDescriptor() == null)
            throw new MissingApplicationDescriptorException();

        if (appConfiguration.getEnvironment() == null)
            throw new MissingEnvironmentException();

        SparkSession.Builder builder = new SparkSession.Builder();

        useApplicationDescriptor(builder);
        useEnvironment(builder);

        sparkSession = builder.getOrCreate();
        sparkContext = sparkSession.sparkContext();
    }

    private void useApplicationDescriptor(SparkSession.Builder builder) {
        ApplicationDescriptor descriptor = appConfiguration.getApplicationDescriptor();

        builder.appName(descriptor.getName());
    }

    private void useEnvironment(SparkSession.Builder builder) {
        Environment environment = appConfiguration.getEnvironment();
        builder.master(environment.getSparkMasterUrl());
        builder.config("hive.metastore.uris", environment.getHiveMetastoreUrl());

    }
}
