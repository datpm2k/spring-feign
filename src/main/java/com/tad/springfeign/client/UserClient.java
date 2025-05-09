package com.tad.springfeign.client;

import com.tad.springfeign.dto.UserDetailsDto;
import feign.Param;
import feign.RequestLine;

public interface UserClient {

    @RequestLine("GET /api/v1/users/{userId}")
    UserDetailsDto getUserById(@Param("userId") String userId);
}
