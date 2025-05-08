package com.tad.springfeign.dto.core.accounts;

import com.tad.springfeign.dto.api.request.GetAccountsRequest;
import jakarta.annotation.Nonnull;

public record CoreGetAccountsRequest(String cif) {

    public static CoreGetAccountsRequest from(@Nonnull GetAccountsRequest request) {
        return new CoreGetAccountsRequest(request.cif());
    }
}
