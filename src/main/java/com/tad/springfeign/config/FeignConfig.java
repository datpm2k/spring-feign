package com.tad.springfeign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tad.springfeign.client.AccountClient;
import com.tad.springfeign.client.UserClient;
import feign.*;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Configuration
public class FeignConfig {

    private final ObjectMapper mapper;
    private final FeignErrorDecoder errorDecoder;
    private final FeignLogger logger;

    @Bean
    @ConfigurationProperties(prefix = "feign.client.config.accounts-service")
    FeignClientProperties accountsServiceClientProperties() {
        return new FeignClientProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "feign.client.config.profiles-service")
    FeignClientProperties profilesServiceClientProperties() {
        return new FeignClientProperties();
    }

    @Bean
    UserClient userClient(
            @Qualifier("profilesServiceClientProperties") FeignClientProperties properties
    ) {
        return feignBuilder(properties)
                .target(UserClient.class, properties.getUrl());
    }

    @Bean
    AccountClient accountClient(
            @Qualifier("accountsServiceClientProperties") FeignClientProperties properties,
            @Qualifier("feignRequestInterceptor") RequestInterceptor requestInterceptor,
            @Qualifier("coreResponseDecoder") Decoder decoder
    ) {
        return feignBuilder(properties)
                .decoder(decoder)
                .requestInterceptor(requestInterceptor)
                .target(AccountClient.class, properties.getUrl());
    }

    Feign.Builder feignBuilder(FeignClientProperties properties) {
        return Feign.builder()
                .encoder(new JacksonEncoder(mapper))
                .decoder(new JacksonDecoder(mapper))
                .errorDecoder(errorDecoder)
                .logLevel(feignLoggerLevel())
                .logger(logger)
                .options(feignRequestOption(properties))
                .retryer(Retryer.NEVER_RETRY);
    }

    Request.Options feignRequestOption(FeignClientProperties properties) {
        return new Request.Options(
                properties.getConnectTimeout(),
                TimeUnit.SECONDS,
                properties.getReadTimeout(),
                TimeUnit.SECONDS,
                properties.isFollowRedirects()
        );
    }

    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
