package com.tad.springfeign.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeignClientProperties {
    private String url;
    private long connectTimeout;
    private long readTimeout;
    private boolean followRedirects;
}
