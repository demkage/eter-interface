package com.eter.docker.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by rusifer on 5/9/17.
 */
public class ApplicationSubmissionBuilder {
    private ApplicationSubmission applicationSubmission;
    private String sparkMaster;

    @Autowired
    public void setSparkMaster(@Value("${eter.spark.cluster.url}") String sparkMaster) {
        this.sparkMaster = sparkMaster;
    }

    public ApplicationSubmission build(Application application) {
        applicationSubmission = new ApplicationSubmission();
        applicationSubmission.setAction("CreateSubmissionRequest");
        applicationSubmission.setClientSparkVersion("2.3.0-SNAPSHOT");
        Optional<String> appArgs = Optional.ofNullable(application.getAppArgs());
        applicationSubmission.setAppArgs(appArgs.orElse("").split(" "));
        Map<String, String> environmentVariables = new HashMap<>();
        environmentVariables.put("SPARK_ENV_LOADED", "1");
        applicationSubmission.setEnvironmentVariables(environmentVariables);
        applicationSubmission.setMainClass(application.getMainClass());
        applicationSubmission.setAppResource(application.getAppPath());
        Map<String, String> sparkProperties = new HashMap<>();
        sparkProperties.put("spark.jars", application.getAppPath());
        sparkProperties.put("spark.driver.supervise", "false");
        sparkProperties.put("spark.submit.deployMode", "cluster");
        sparkProperties.put("spark.master", sparkMaster);
        sparkProperties.put("spark.driver.extraClassPath", "/usr/local/hive/lib/hive-exec-3.0.0-SNAPSHOT.jar" +
                ":/usr/local/hive/lib/hive-metastore-3.0.0-SNAPSHOT.jar:" +
                "/usr/local/apps/spark-sql_2.11-2.3.0-SNAPSHOT.jar" +
                ":/usr/local/apps/dependencies/spark-core_2.11-2.3.0-SNAPSHOT.jar" +
                ":/usr/local/apps/dependencies/spark-streaming_2.11-2.3.0-SNAPSHOT.jar" +
                ":/usr/local/apps/dependencies/spark-catalyst_2.11-2.3.0-SNAPSHOT.jar" +
                ":/usr/local/apps/dependencies/spark-hive_2.11-2.3.0-SNAPSHOT.jar" +
                ":/usr/local/hadoop/share/hadoop/hdfs/hadoop-hdfs-2.8.0.jar" +
                ":/usr/local/hadoop/share/hadoop/hdfs/hadoop-hdfs-client-2.8.0.jar" +
                ":/usr/local/apps/parquet-column-1.8.2.jar" +
                ":/usr/local/apps/parquet-hadoop-1.8.2.jar");
        sparkProperties.put("spark.app.name", application.getName());
        sparkProperties.put("spark.executor.extraClassPath", "/usr/local/hive/lib/*");
        sparkProperties.put("spark.eventLog.enabled", "false");
        applicationSubmission.setSparkProperties(sparkProperties);

        return applicationSubmission;
    }

}
