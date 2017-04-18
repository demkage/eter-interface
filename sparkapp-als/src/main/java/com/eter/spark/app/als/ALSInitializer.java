package com.eter.spark.app.als;

import com.eter.spark.app.template.AppConfiguration;
import com.eter.spark.app.template.SparkSessionInitializer;
import com.eter.spark.app.template.exceptions.SparkAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ALSInitializer extends SparkSessionInitializer {

    @Autowired
    public ALSInitializer(AppConfiguration appConfiguration) {
        super(appConfiguration);

        try {
            init();
        } catch (SparkAppException e) {
            e.printStackTrace();
        }
    }
}
