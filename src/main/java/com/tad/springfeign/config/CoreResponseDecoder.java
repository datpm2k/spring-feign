package com.tad.springfeign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tad.springfeign.dto.api.CoreBaseResponse;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@RequiredArgsConstructor
@Slf4j
@Component
public class CoreResponseDecoder implements Decoder {

    private final ObjectMapper mapper;

    @Override
    public Object decode(Response response, Type type) throws FeignException {
        try {
            CoreBaseResponse<?> baseResponse = mapper.readValue(
                    response.body().asInputStream(),
                    CoreBaseResponse.class
            );

            baseResponse.check();

            return baseResponse.data();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("API Error: " + e.getMessage()); // throw system error
        }
    }
}
