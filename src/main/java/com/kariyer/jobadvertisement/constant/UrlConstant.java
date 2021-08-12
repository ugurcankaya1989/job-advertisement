package com.kariyer.jobadvertisement.constant;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource({"classpath:restservicepath.properties"})
public class UrlConstant {
    public static class ElasticsearchUrl{
        @Value("context.url")
        public static final String ELASTICSEARCH_URL = null;
    }
}
