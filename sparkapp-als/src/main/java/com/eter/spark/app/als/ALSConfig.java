package com.eter.spark.app.als;

import com.eter.spark.app.template.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ALSConfig {

    @Bean
    public Environment environment() {
        return new BootEnvironment();
    }

    @Bean
    public ApplicationDescriptor applicationDescriptor() {
        return new BootApplicationDescriptor();
    }

    @Bean
    public AppConfiguration appConfiguration(Environment environment, ApplicationDescriptor applicationDescriptor) {
        return new AppConfiguration(environment, applicationDescriptor);
    }

    @Bean
    public Extractor extractor() {
        return new RatingsExtractor();
    }
}
