package com.eter.spark.app.template.exceptions;

/**
 * Created by abosii on 4/18/2017.
 */
public class MissingEnvironmentException extends SparkAppException {
    public MissingEnvironmentException() {
        super("Environment instance is null in configuration");
    }
}
