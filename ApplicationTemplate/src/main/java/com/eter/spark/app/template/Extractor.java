package com.eter.spark.app.template;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public interface Extractor {
    Dataset<Row> extract(SparkSession sparkSession);
}
