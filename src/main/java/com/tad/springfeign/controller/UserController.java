package com.tad.springfeign.controller;

import com.tad.springfeign.client.UserClient;
import com.tad.springfeign.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserClient userClient;

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDetailsDto> getUserDetail(@PathVariable("userId") String userId) {
        log.info("get user by id {}", userId);
        return ResponseEntity.ok(userClient.getUserById(userId));
    }
}
