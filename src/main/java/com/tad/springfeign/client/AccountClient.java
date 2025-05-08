package com.tad.springfeign.client;

import com.tad.springfeign.dto.AccountDto;
import com.tad.springfeign.dto.core.accounts.CoreGetAccountsRequest;
import feign.RequestLine;

import java.util.List;

public interface AccountClient {

    @RequestLine("POST /api/v1/accounts")
    List<AccountDto> getAccounts(CoreGetAccountsRequest request);
}
