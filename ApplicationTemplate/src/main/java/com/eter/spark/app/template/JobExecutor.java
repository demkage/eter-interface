package com.eter.spark.app.template;

import com.eter.spark.app.template.exceptions.SparkAppException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * Created by abosii on 4/18/2017.
 */
public class JobExecutor {
    private SparkSession sparkSession;
    private Extractor extractor;
    private SparkSessionInitializer initializer;

    public void init(Configuration configuration, SparkSessionInitializer initializer) throws SparkAppException {
        this.initializer = initializer;
        initializer.setConfiguration(configuration);
        initializer.run();
        sparkSession = initializer.getSparkSession();
    }

    public void setExtractor(Extractor extractor) {
        this.extractor = extractor;
    }

    public void execute() {
        if (extractor == null)
            throw new NullPointerException();

        Dataset<Row> dataset = extractor.extract(sparkSession);

    }
}
