package com.eter.docker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Created by rusifer on 5/13/17.
 */
@Configuration
public class ConcurrentConfig {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(@Value("${eter.concurrent.poolSize:5}") int poolSize) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler =
                new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(poolSize);
        threadPoolTaskScheduler.setThreadNamePrefix("EterThreadPool");
        return threadPoolTaskScheduler;
    }
}
