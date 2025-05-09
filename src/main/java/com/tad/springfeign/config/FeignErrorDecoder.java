package com.tad.springfeign.config;

import com.tad.springfeign.exception.NotFoundException;
import com.tad.springfeign.exception.SystemErrorException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 404) {
            return new NotFoundException("Not found");
        }
        return new SystemErrorException("System error");
    }
}
