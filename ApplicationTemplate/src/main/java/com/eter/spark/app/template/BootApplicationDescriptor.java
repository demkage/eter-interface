package com.eter.spark.app.template;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class BootApplicationDescriptor implements ApplicationDescriptor {
    @Value("com.eter.spark.app.id")
    private Long id;
    @Value("com.eter.spark.app.name")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
