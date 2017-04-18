package com.eter.spark.app.template;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BootEnvironment implements Environment {
    @Value("com.eter.spark.master.url")
    private String sparkMasterUrl;
    @Value("com.eter.hive.metastore.url")
    private String hiveMetastoreUrl;

    public String getSparkMasterUrl() {
        return sparkMasterUrl;
    }

    public String getHiveMetastoreUrl() {
        return hiveMetastoreUrl;
    }
}
