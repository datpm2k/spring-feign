package com.tad.springfeign.controller;

import com.tad.springfeign.client.AccountClient;
import com.tad.springfeign.dto.AccountDto;
import com.tad.springfeign.dto.api.request.GetAccountsRequest;
import com.tad.springfeign.dto.core.accounts.CoreGetAccountsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountClient accountClient;

    @PostMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts(@RequestBody GetAccountsRequest request) {
        return ResponseEntity.ok(
                accountClient.getAccounts(CoreGetAccountsRequest.from(request))
        );
    }
}
