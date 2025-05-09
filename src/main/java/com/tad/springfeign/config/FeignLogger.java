package com.tad.springfeign.config;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class FeignLogger extends Logger {

    @Override
    protected void log(String configKey, String format, Object... args) {
        log.debug(format(configKey, format, args));
    }

    protected String format(String configKey, String format, Object... args) {
        return String.format(methodTag(configKey) + format, args);
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        super.logRequest(configKey, logLevel, request);

        byte[] bodyBytes = request.body();
        if (bodyBytes == null) bodyBytes = new byte[]{};
        String body = new String(bodyBytes);

        log.info("FEIGN REQUEST URL= {} METHOD= {}", request.url(), request.httpMethod().name());
        log.info("FEIGN REQUEST BODY= {}", body);
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        log.info("FEIGN RESPONSE URL= {}, METHOD= {}, statusCode={}, in= {}ms",
                response.request().url(), response.request().httpMethod().name(), response.status(), elapsedTime);

        byte[] bodyBytes = Util.toByteArray(response.body().asInputStream());
        if (bodyBytes == null) bodyBytes = new byte[]{};
        String body = new String(bodyBytes);

        body = body.replaceAll("[\\r\\n\\t\\s+]+", " ").trim();

        log.info("FEIGN RESPONSE BODY= {}", body);

        return response.toBuilder().body(bodyBytes).build();
    }
}
