package com.eter.spark.app.template;

/**
 * Created by abosii on 4/18/2017.
 */
public interface Environment {
    String getJarFilename();

    String getSparkMasterHostname();

    int getSparkMasterPort();

    String getSparkMasterUrl();

    String getHiveMetastoreHostname();

    int getHiveMetastorePort();

    String getHiveMetastoreUrl();
}
