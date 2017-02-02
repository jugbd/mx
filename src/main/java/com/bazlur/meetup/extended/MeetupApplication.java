package com.bazlur.meetup.extended;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;


@SpringBootApplication
@EnableConfigurationProperties(MxProperties.class)
@EnableCaching
public class MeetupApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetupApplication.class, args);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache("meetup.schedules", initConfiguration(Duration.TEN_MINUTES));
            cm.createCache("github.user", initConfiguration(Duration.ONE_HOUR));
        };
    }

    private MutableConfiguration<Object, Object> initConfiguration(Duration duration) {
        return new MutableConfiguration<>()
                .setStoreByValue(false)
                .setStatisticsEnabled(true)
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(duration));
    }
}
