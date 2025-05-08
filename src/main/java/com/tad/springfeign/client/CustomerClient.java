package com.tad.springfeign.client;

import com.tad.springfeign.dto.CustomerDto;
import feign.RequestLine;
import org.springframework.web.bind.annotation.PathVariable;

public interface CustomerClient {

    @RequestLine("GET /v1/customer/{cif}")
    CustomerDto getCustomerByCif(@PathVariable("cif") String cif);
}
