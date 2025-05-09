package com.tad.springfeign.dto;

public record BaseResponse<T>(String code, String message, T data) {
}
