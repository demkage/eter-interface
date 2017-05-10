package com.eter.docker.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.eter.docker.domain.ApplicationSubmissionBuilder;
import com.eter.docker.util.log.LoggingRequestInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rusifer on 5/9/17.
 */
@Configuration
public class RESTConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<>();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        interceptorList.add(new LoggingRequestInterceptor());
        converters.add(new MappingJackson2HttpMessageConverter());
        //restTemplate.setInterceptors(interceptorList);
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }

    @Bean
    public ApplicationSubmissionBuilder applicationSubmissionBuilder() {
        return new ApplicationSubmissionBuilder();
    }
}
