package com.eter.spark.app.template.exceptions;

/**
 * Created by abosii on 4/18/2017.
 */
public class MissingApplicationDescriptorException extends SparkAppException {
    public MissingApplicationDescriptorException() {
        super("ApplicationDescriptor instance is null in configuration");
    }
}
