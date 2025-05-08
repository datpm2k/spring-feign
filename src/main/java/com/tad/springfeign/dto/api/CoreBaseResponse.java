package com.tad.springfeign.dto.api;

import java.util.Objects;

public record CoreBaseResponse<T>(String code,
                                  String message,
                                  T data) {

    public static final String SUCCESS_CODE = "000000";

    public boolean success() {
        return Objects.equals(SUCCESS_CODE, code);
    }

    public void check() {
        if (!success()) {
            throw new RuntimeException("API Error code: " + code + " message: " + message);
        }
    }
}
