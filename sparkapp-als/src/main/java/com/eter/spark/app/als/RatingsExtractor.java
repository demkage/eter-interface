package com.eter.spark.app.als;

import com.eter.spark.app.template.Extractor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Component;


@Component
public class RatingsExtractor implements Extractor {
    @Override
    public Dataset<Row> extract(SparkSession sparkSession) {
        return sparkSession.sql("SELECT * FROM productsratings");
    }
}
